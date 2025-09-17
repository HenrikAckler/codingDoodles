public class Student{
   Student[] veto; //students they will not work with
   String[] projects; //preferred projects, where index equals ranking, higher is better
   String name; //student's name

   /**Make a new student
    * 
    * @param name Student's Name
    * @param v number of vetos
    * @param r number of project ranks
    */
   public Student(String name, int v, int r){
      veto = new Student[v];
      projects = new String[r];
      this.name = name;
   }

   /**Populate ranked choice projects of a student. Will return early if array is too short.
    * 
    * @param projs array of projects
    */
   public void setProjects(String[] projs){
      if (projs.length < projects.length) return;
      for(int i = 0; i < projects.length; i++){ //deep copy
         projects[i] = projs[i];
      }
   }

   /**Populate student vetos, will return early if array is too short
    * 
    * @param vs array of students to veto
    */
   public void setVetos(Student[] vs){
      if (vs.length < veto.length) return;
      for(int i = 0; i < veto.length; i++){ //deep copy
         veto[i] = vs[i];
      }
   }

   public String getName(){
      return name;
   }

   public String[] getProjs(){
      return projects;
   }

   public Student[] getVetos(){
      return veto;
   }

   public boolean equals(Student s){
      return name.equals(s.getName());
   }

   public int getCompatibility(Student s){
      //check if this vetos s
      for(Student x : veto){
         if (x.equals(s)) return -1;
      }
      //check if s vetos this
      for(Student x : s.getVetos()){
         if (this.equals(x)) return -1;
      }

      //find ranked compatibility
      int rank = 0;
      String[] sProj = s.getProjs();
      for (int i = 0; i < projects.length; i++){
         for(int j = 0; j < sProj.length; j++){
            if(projects[i].equals(sProj[j])){
               rank += i + j; //add the rank values
            }
         }
      }

      return rank;
   }
}