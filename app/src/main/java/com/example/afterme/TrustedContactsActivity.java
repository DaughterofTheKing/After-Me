package com.example.afterme;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import com.example.afterme.database.AppDatabase;
import com.example.afterme.database.TrustedContact;
public class TrustedContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trusted_contacts);
        findViewById(R.id.btnSaveContact).setOnClickListener(v -> {
            EditText nameField = findViewById(R.id.editContactName);
            String contactName = nameField.getText().toString().trim();
            EditText relationshipField = findViewById(R.id.editRelationship);
            String relationship = relationshipField.getText().toString().trim();
            EditText phoneField = findViewById(R.id.editPhoneNumber);
            String phoneNumber = phoneField.getText().toString().trim();
            TextView status = findViewById(R.id.textContactStatus);
            if (contactName.isEmpty()) {
                status.setText("Please enter a contact name.");
                return;
            }
            if (relationship.isEmpty()) {
                status.setText("Please enter a relationship.");
                return;
            }
            if (phoneNumber.isEmpty()) {
                status.setText("Please enter a phone number.");
                return;
            }
            TrustedContact contact = new TrustedContact();
            contact.name = contactName;
            contact.relationship = relationship;
            contact.phoneNumber = phoneNumber;
            AppDatabase db = Room.databaseBuilder(
                    getApplicationContext(),
                    AppDatabase.class,
                    "after_me_database"
            ).build();
            new Thread(() -> {
                db.trustedContactDao().insert(contact);
            }).start();
            status.setText("Contact: " + contactName
                    + "\nRelationship: " + relationship
                    + "\nPhone: " + phoneNumber);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}