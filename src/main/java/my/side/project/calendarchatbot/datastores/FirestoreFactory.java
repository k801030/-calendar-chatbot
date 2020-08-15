package my.side.project.calendarchatbot;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import my.side.project.calendarchatbot.utils.LogLevel;
import my.side.project.calendarchatbot.utils.Output;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FirestoreFactory {

    private static final Output output = Output.getOutput(FirestoreFactory.class.getName());

    public static Firestore create() throws IOException {
        String keyPath = System.getenv("KEY_PATH");
        output.print(LogLevel.DEBUG, "keyPath={}", keyPath);
        InputStream serviceAccount = new FileInputStream(keyPath);
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(credentials)
            .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();
        return db;
    }
}
