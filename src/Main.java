public  class Main {
    public static void main(String[] args) {
        Map m = new Map(50, 100);
        System.out.println(m.getElevation(40, 60));

        Position p = new Position(40, 60);
        System.out.println(m.getElevation(p));
        Zebra z1 = new Zebra(p, 5, false);
    }
}