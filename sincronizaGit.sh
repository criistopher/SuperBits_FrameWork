find . -name target-type d -exec rm -rf {} \;

git pull
git add --all
git commit -m "Atualizacao automática"
git push
chmod +777 * -R
cd /home/superBits/projetos/Super_Bits/source/SuperBits_FrameWork/SB_FRAMEWORK/
mvn clean install  -Dmaven.test.skip=true




