DIRETORIO_PROJETO=`pwd`
DIRETORIO_WEBAPP_SERVLET=$DIRETORIO_PROJETO/src/main/webapp
DIRETORIO_RESOURCES=$DIRETORIO_PROJETO/src/main/webapp/resources

DIRETORIO_ORIGEM_SB_COMP=/home/superBits/recursos/SBComp
DIRETORIO_ORIGEM_WEB_INF=/home/superBits/recursos/WEB-INF

if [ ! -d $DIRETORIO_WEBAPP_SERVLET ]; then
  echo "Diretorio webApp não existe, certifique que este é um projeto web"
  exit 1
fi
if [ ! -d $DIRETORIO_WEBAPP_SERVLET ]; then
  echo "Diretorio resources não existe, certifique que este é um projeto web"
  exit 1
fi

cd $DIRETORIO_ORIGEM_SB_COMP
./sincroniza.sh
cd $DIRETORIO_ORIGEM_WEB_INF
./sincronizar.sh

rsync -Cravzp --exclude='*/.git' --exclude='*/target' $DIRETORIO_ORIGEM_SB_COMP  $DIRETORIO_RESOURCES
rsync -Cravzp --exclude='*/.git' --exclude='*/target' $DIRETORIO_ORIGEM_WEB_INF  $DIRETORIO_WEBAPP_SERVLET

#cd resources

