#!/bin/bash

# Vérifier si numpy est installé, sinon l'installer
if ! python3 -c "import numpy"; then
    pip3 install numpy
fi
# Vérifier si pandas est installé, sinon l'installer
if ! python3 -c "import pandas"; then
    pip3 install pandas
fi

# Vérifier si seaborn est installé, sinon l'installer
if ! python3 -c "import seaborn"; then
    pip3 install seaborn
fi
#verifier si ast est installé, sinon l'installer
if ! python3 -c "import ast"; then
    pip3 install ast
fi

# Vérifier si geopy est installé, sinon l'installer
if ! python3 -c "from geopy.distance import geodesic"; then
    pip3 install geopy
fi

# Vérifier si scipy est installé, sinon l'installer
if ! python3 -c "import scipy.stats"; then
    pip3 install scipy
fi

# Vérifier si networkx est installé, sinon l'installer
if ! python3 -c "import networkx"; then
    pip3 install networkx
fi

# Vérifier si matplotlib est installé, sinon l'installer
if ! python3 -c "import matplotlib.pyplot"; then
    pip3 install matplotlib
fi

# Vérifier si geopandas est installé, sinon l'installer
if ! python3 -c "import geopandas"; then
    pip3 install geopandas
fi
