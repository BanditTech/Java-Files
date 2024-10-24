public class inheritence {
    public static void main(String[] args) {
        variant myTest = new variant();
        System.out.println(myTest.Always);
        System.out.println(myTest.origin());
        System.out.println(myTest.BestNumber);
        inheritence originClass = new inheritence();
        System.out.println(originClass.origin());
    }
    public String origin(){
        return "Origin Text";
    }
    public static String Always = "This never changes";
    public static int BestNumber = 69420;
}