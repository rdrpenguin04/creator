jar xf Creator2D.jar native/
jar xf Creator2D.jar assets/
java -Djava.library.path="native" -jar Creator2D.jar 1
rd /s native/
rd /s assets/
