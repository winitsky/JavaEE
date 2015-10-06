MKDIR d:\ibank\
cd /D d:\ibank\
git clone -b spring https://github.com/winitsky/JavaEE.git


cd /d d:\ibank\JavaEE\
call mvn install


cd /d d:\ibank\JavaEE\
call mvn tomcat7:redeploy
