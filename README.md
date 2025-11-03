# BYT Lab 1 - Unit tests
Java project for practicing JUnit 4 unit testing.  
Build and run tests using plain 'javac' and 'java'.

## Setup
### 1. Clone the repository
```
git clone git@github.com:sariiev/byt_lab_1.git

cd byt_lab_1
```

### 2. Download libraries
```
mkdir -p lib

curl -o lib/hamcrest-core-1.3.jar https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

curl -o lib/junit-4.13.2.jar https://repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar
```

### 3. Create output directories
```
mkdir -p bin/src
mkdir -p bin/test
```

### 4. Compile sources
```
javac -d bin/src $(find src -type f -name "*.java")
```

### 5. Compile tests
```
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src" -d bin/test $(find test -type f -name "*.java")
```

### 6. Run tests
a_Introductory
```
java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore a_Introductory.FibonacciTest

java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore a_Introductory.PointTest

java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore a_Introductory.LineTest

java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore a_Introductory.Vector2DTest

java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore a_Introductory.QuadrilateralTest
```
b_Money
```
java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore b_Money.CurrencyTest

java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore b_Money.MoneyTest

java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore b_Money.AccountTest

java -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:bin/src:bin/test" org.junit.runner.JUnitCore b_Money.BankTest
```