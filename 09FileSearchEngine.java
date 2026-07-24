package search;

import java.io.*;
import java.util.*;


public class FileSearch {


public List<File> search(
File directory,
String keyword)
{

List<File> results =
new ArrayList<>();


File[] files =
directory.listFiles();


if(files == null)
return results;



for(File file: files){


if(file.isDirectory()){

results.addAll(
search(file,keyword)
);

}


else if(file.getName()
.contains(keyword)){

results.add(file);

}

}


return results;

}

}
