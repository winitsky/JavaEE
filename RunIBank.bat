MKDIR d:\ibank\
cd /D d:\ibank\
git clone -b develop https://github.com/winitsky/JavaEE.git


cd /d d:\ibank\JavaEE\
call mvn install

mysql.exe -u root -p < D:\ibank\JavaEE\internetbank.dao\banking.sql

cd internetbank.dao\target\
java -jar internetbank.dao-0.0.1-SNAPSHOT.jar \dao\impl.RunDAO