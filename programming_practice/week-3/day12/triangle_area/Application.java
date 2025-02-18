public class Application {

    public static void main(String[] args) {
        Input input = new Input();
        DiagramFactory diagramFactory = new DiagramFactory(input);
        Diagram diagram = diagramFactory.createDiagramByInput();
        Plane plane = new Plane(diagram);

        System.out.println(plane);
        System.out.println(diagram);
    }

}
