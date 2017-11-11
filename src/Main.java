public  class Main {
    public static void main(String[] args) {
        Map m = new Map(50, 100);
        System.out.println(m.getElevation(3.5, 2.5));

        Position p = new Position(7.3, 8.2);
        System.out.println(m.getElevation(p));
    }
}