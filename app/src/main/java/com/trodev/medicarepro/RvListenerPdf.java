package com.trodev.medicarepro;
import com.trodev.medicarepro.adapter.AdapterPdf;
import com.trodev.medicarepro.models.ModelPdf;

public interface RvListenerPdf {

    void onPdfClick(ModelPdf modelPdf, int position);
    void onPdfMoreClick(ModelPdf modelPdf, int position, AdapterPdf.HolderPdf holder);
}
