public class Team {
   Student[] team;

   public Team(int size){
      team = new Student[size];
   }

   public int getCompatibility(){
      boolean fail = false;
      int comp = 0;
      int tmp;

      for(Student s : team){
         for(Student i : team){ //found the i in team
            tmp = s.getCompatibility(i);
            fail = tmp != -1 ? false : true;
            comp += tmp;
         }
      }

      return fail ? -1 : comp; //if we failed, return -1, else the comp value
   }
}
