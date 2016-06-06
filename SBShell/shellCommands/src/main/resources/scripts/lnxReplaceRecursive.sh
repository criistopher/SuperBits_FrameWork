#!/bin/bash
cd :pastaRecursiva 
find ./ -name '*.xml' -exec perl -i -p -e 's/:textoAntigo/:novoTexto/ig;' {} +
 

