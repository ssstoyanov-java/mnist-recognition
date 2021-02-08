public class Shared {
    private static DrawWindow drawWindow;
    private static DrawPanel drawPanel;
    private static RecognitionWindow recognitionWindow;

    public static DrawWindow getDrawWindow() {
        return drawWindow;
    }

    public static void setDrawWindow(DrawWindow drawWindow) {
        Shared.drawWindow = drawWindow;
    }

    public static DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public static void setDrawPanel(DrawPanel drawPanel) {
        Shared.drawPanel = drawPanel;
    }

    public static RecognitionWindow getRecognitionWindow() {
        return recognitionWindow;
    }

    public static void setRecognitionWindow(RecognitionWindow recognitionWindow) {
        Shared.recognitionWindow = recognitionWindow;
    }
}
