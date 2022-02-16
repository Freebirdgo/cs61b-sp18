
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int length = keyboard.length();

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString[] stringTable = new synthesizer.GuitarString[length];
        for(int i = 0; i < length; i++) {
            stringTable[i] = new synthesizer.GuitarString(CONCERT_A * Math.pow(2, (i - 24) / 12.0));
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keyboard.contains(String.valueOf(key))) {
                    stringTable[keyboard.indexOf(key)].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for(int i = 0; i < length; i++) {
                sample += stringTable[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(int i = 0; i < length; i++) {
                stringTable[i].tic();
            }
        }
    }
}

