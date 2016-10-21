DIRETORIO_PROJETO=`pwd`
DIRETORIO_WEBAPP_SERVLET=$DIRETORIO_PROJETO/src/main/webapp
DIRETORIO_RESOURCES=$DIRETORIO_PROJETO/src/main/webapp/resources

DIRETORIO_SB_COMP_RESOURCES=/home/superBits/recursos/SBComp
DIRETORIO_ORIGEM_WEB_INF=/home/superBits/recursos/WEB-INF

if [ ! -d $DIRETORIO_WEBAPP_SERVLET ]; then
  echo "Diretorio webApp não existe, certifique que este é um projeto web"
  exit 1
fi
if [ ! -d $DIRETORIO_WEBAPP_SERVLET ]; then
  echo "Diretorio resources não existe, certifique que este é um projeto web"
  exit 1
fi



rsync -Cravzp --exclude='*/.git' --exclude='*/target'  $DIRETORIO_RESOURCES/SBComp  $DIRETORIO_SB_COMP_RESOURCES
cd $DIRETORIO_SB_COMP_RESOURCES
./sincroniza.sh


#cd resources

