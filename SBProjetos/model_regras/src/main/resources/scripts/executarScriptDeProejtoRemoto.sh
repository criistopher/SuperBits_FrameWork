#!/bin/bash
echo "Executando :scriptProjeto em servidor "
ssh git@homologacao.superkompras.com.br 'bash -s' < :scriptProjeto :projeto