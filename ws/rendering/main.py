import numpy as np
import pandas as pd
import seaborn as sns
import ast as ast
from geopy.distance import geodesic
import scipy.stats as sps
import networkx as nx
import matplotlib.pyplot as plt
import warnings
warnings.filterwarnings('ignore')
import geopandas
import sys

plt.rcParams["figure.figsize"] = (20,15)
communes=pd.read_csv("./rendering/DonneesFourniesGraphes/voisinageCommunesBretonnes.csv", sep=';')
geo=pd.read_csv("./rendering/DonneesFourniesGraphes/communes-geo.csv", sep=';')
region = geopandas.read_file("./rendering/DonneesFourniesGraphes/communes-geo.geojson")
gare=pd.read_csv("./rendering/DonneesFourniesGraphes/gare.csv", sep=';')

#Liste des communes possédant un aeroport
aeroport =[22113,22373,29075,29151,29216,35228,35238,56162]


def amezek(com):
    voisins = [int(num) for num in communes['insee_voisins'][com].split('|')]
    amezek = []
    for vois in voisins:
        if (22000 <= vois and vois < 23000) or (29000 <= vois and vois < 30000) or (35000 <= vois and vois < 36000) or (
                56000 <= vois and vois < 57000):  # on reste en bretagne
            if vois != communes['insee'][com]:  # boucle
                amezek.append(vois)
    return amezek


for x in range(len(communes['insee'])):
    amezek(x)

voisins_dict = {communes['insee'][x]: amezek(x) for x in range(len(communes['insee']))}
G = nx.from_dict_of_lists(voisins_dict)

geo_lite = geo.iloc[:, [0, 1, 17, 19]]

geo_lite.columns
geo_lite['Latitude'] = geo_lite['Geo Point'].apply(lambda x: ast.literal_eval(x)[0])
# création de la colone Longitude
geo_lite['Longitude'] = geo_lite['Geo Point'].apply(lambda x: ast.literal_eval(x)[1])


def pos_insee(G, data):
    pos = {}  # dictionnaire vide
    for com in G.nodes:
        y = float(data[data['Code Officiel Commune'] == com]['Latitude'].iloc[0])
        x = float(data[data['Code Officiel Commune'] == com]['Longitude'].iloc[0])
        # on peut adapter les coordonnées
        # x=(x- 48.13380133652042)*1000
        # y=(y+2.287539276431153)*1000

        pos[com] = [x, y]

    return pos


pos_insee = pos_insee(G, geo_lite)


def label_insee(G, data):
    label = {}  # dictionnaire vide
    for com in G.nodes:
        lab = data[data['Code Officiel Commune'] == com]['Nom Officiel Commune'].iloc[0]

        label[com] = lab

    return label


label_insee = label_insee(G, geo_lite)


# Utilisation de dijkstra vu en cours pour trouver la distance de chaque point de start, ici graphe doit etre le dictionaire contenant pour chaque noeud ses voisins
def dijkstra(graph, start):
    distances = {node: -1 for node in graph}
    distances[start] = 0
    queue = [(0, start)]
    while queue:
        queue.sort()
        current_distance, current_node = queue.pop(0)
        if current_distance <= distances[current_node]:
            for neighbor in graph[current_node]:
                distance = current_distance + 1
                if distances[neighbor] == -1 or distance < distances[neighbor]:
                    distances[neighbor] = distance
                    queue.append((distance, neighbor))

    # suppression des noeuds innaccessibles
    toRemove = []
    for i in distances:
        if distances.get(i) == -1:
            toRemove.append(i)
    for u in toRemove:
        distances.pop(u)
    return distances


def trace_graphe_excentricites(G, pos, insee_commune):
    # Trouver les nœuds accessibles depuis la commune donnée
    # accessible_nodes = nx.single_source_shortest_path_length(G, source=insee_commune)

    accessible_nodes = dijkstra(voisins_dict, insee_commune)

    # Définir la taille des nœuds en fonction de leur excentricité (par exemple, taille inversement proportionnelle à l'excentricité)
    node_sizes = [1 / (accessible_nodes[node] + 1) * 100 for node in
                  accessible_nodes]  # Ajuster le facteur 100 en fonction de la taille souhaitée

    # Créer les étiquettes pour chaque nœud accessible
    labels = {node: str(accessible_nodes[node]) for node in accessible_nodes}

    # Dessiner le graphe
    plt.close()
    ax = region.plot(linewidth=1, edgecolor="grey", facecolor="lightblue")
    ax.axis("off")

    # Ajouter les nœuds accessibles avec une taille proportionnelle à leur excentricité
    nx.draw_networkx_nodes(G, pos=pos, nodelist=accessible_nodes, node_size=node_sizes, alpha=0.8, node_color='pink')

    # Ajouter les arêtes entre les nœuds accessibles
    nx.draw_networkx_edges(G, pos=pos,
                           edgelist=[(u, v) for u, v in G.edges() if u in accessible_nodes and v in accessible_nodes],
                           width=0.5, alpha=0.4, edge_color="r")

    # Ajouter les étiquettes avec l'excentricité de chaque nœud accessible
    nx.draw_networkx_labels(G, pos=pos, labels=labels, font_size=8, ax=ax)

    plt.savefig("graphe.png")

#29168
trace_graphe_excentricites(G,pos_insee,int(sys.argv[1]))