public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // Test String concatenation (inefficient)
        long startTime = System.nanoTime();
        String result1 = concatenateWithString(1000);
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");

        // Test StringBuilder concatenation (fast)
        startTime = System.nanoTime();
        String result2 = concatenateWithStringBuilder(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) + " ns");

        // Test StringBuffer concatenation (thread-safe)
        startTime = System.nanoTime();
        String result3 = concatenateWithStringBuffer(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) + " ns");

        // Demonstrate StringBuilder methods
        System.out.println("\n=== STRINGBUILDER METHODS DEMO ===");
        demonstrateStringBuilderMethods();

        // Demonstrate thread safety
        System.out.println("\n=== THREAD SAFETY DEMO ===");
        demonstrateThreadSafety();

        // Compare string comparison methods
        System.out.println("\n=== STRING COMPARISON DEMO ===");
        compareStringComparisonMethods();

        // Demonstrate memory efficiency
        System.out.println("\n=== MEMORY EFFICIENCY DEMO ===");
        demonstrateMemoryEfficiency();
    }

    // Inefficient String concatenation
    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }

    // Efficient (not thread-safe)
    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Efficient (thread-safe)
    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Demonstrating StringBuilder methods
    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        sb.append(" from Java");
        sb.insert(5, " Amazing");
        sb.delete(6, 13);
        sb.deleteCharAt(0);
        sb.reverse();
        sb.replace(0, 4, "JAVA");
        sb.setCharAt(0, 'X');

        System.out.println("Final StringBuilder content: " + sb);
        System.out.println("Capacity: " + sb.capacity());
        sb.ensureCapacity(50);
        System.out.println("Capacity after ensureCapacity(50): " + sb.capacity());
        sb.trimToSize();
        System.out.println("Capacity after trimToSize(): " + sb.capacity());
    }

    // Demonstrating thread safety
    public static void demonstrateThreadSafety() {
        StringBuffer safeBuffer = new StringBuffer("Start");
        StringBuilder unsafeBuilder = new StringBuilder("Start");

        Runnable safeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                safeBuffer.append("A");
            }
        };

        Runnable unsafeTask = () -> {
            for (int i = 0; i < 1000; i++) {
                unsafeBuilder.append("A");
            }
        };

        Thread t1 = new Thread(safeTask);
        Thread t2 = new Thread(safeTask);
        Thread t3 = new Thread(unsafeTask);
        Thread t4 = new Thread(unsafeTask);

        t1.start(); t2.start();
        t3.start(); t4.start();

        try {
            t1.join(); t2.join();
            t3.join(); t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("StringBuffer length (thread-safe): " + safeBuffer.length());
        System.out.println("StringBuilder length (NOT thread-safe): " + unsafeBuilder.length());
    }

    // Comparing string comparison methods
    public static void compareStringComparisonMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("== comparison (str1, str2): " + (str1 == str2));
        System.out.println("== comparison (str1, str3): " + (str1 == str3));
        System.out.println("equals(): " + str1.equals(str3));
        System.out.println("equalsIgnoreCase(): " + str1.equalsIgnoreCase("hello"));
        System.out.println("compareTo(): " + str1.compareTo(str3));
        System.out.println("compareToIgnoreCase(): " + str1.compareToIgnoreCase("hello"));
    }

    // Demonstrating memory efficiency
    public static void demonstrateMemoryEfficiency() {
        Runtime runtime = Runtime.getRuntime();
        long before = runtime.freeMemory();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += "a"; // inefficient
        }
        long after = runtime.freeMemory();
        System.out.println("Memory used by String concatenation: " + (before - after) + " bytes");

        before = runtime.freeMemory();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        after = runtime.freeMemory();
        System.out.println("Memory used by StringBuilder: " + (before - after) + " bytes");

        // Demonstrate string pool
        String a = "Java";
        String b = "Java";
        String c = new String("Java");
        System.out.println("a == b (string pool): " + (a == b));
        System.out.println("a == c (new object): " + (a == c));
    }
}
