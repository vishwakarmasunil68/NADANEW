package com.nada.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.nada.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfViewerActivity extends AppCompatActivity {

    @BindView(R.id.pdfv)
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        ButterKnife.bind(this);

        if(getIntent().getStringExtra("type").equalsIgnoreCase("drugs")){
            pdfView.fromAsset("drugs.pdf").load();
        }else{
            pdfView.fromAsset("1576049703.pdf").load();
        }
    }
}
