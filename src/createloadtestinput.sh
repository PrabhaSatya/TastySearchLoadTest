cat $1 | grep "summary\|text" | sed -e 's/[^a-zA-Z]/ /g;s/  */ /g' | tr '[:upper:]' '[:lower:]' | tr " " "\n" | sort | uniq > $2
javac LoadTest.java
java LoadTest $2 $3 $4
