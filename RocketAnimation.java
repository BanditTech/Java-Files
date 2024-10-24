public class RocketAnimation {

    public static void main(String[] args) throws InterruptedException {
        // Rocket design as a string array
        String[] rocket = {
             "",
             "   __",
             "   \\ \\_____",
             "###[==_____>",
             "   /_/"
        };
        
        // Number of steps to move across the screen
        int screenWidth = 40; // adjust for your console width

        // Loop for animation frames
        for (int i = 0; i < screenWidth; i++) {
            // Clear the console
            System.out.print("\033[H\033[2J"); // Clear console (might not work on some IDEs, works in terminals)
            System.out.flush();

            // Print rocket at new position
            for (String line : rocket) {
                // Print spaces before the rocket to simulate movement
                System.out.println(" ".repeat(i) + line);
            }

            // Delay to create the animation effect
            Thread.sleep(200); // Adjust for speed (200ms delay)
        }
    }
}
