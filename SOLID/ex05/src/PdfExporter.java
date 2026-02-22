import java.nio.charset.StandardCharsets;

public class PdfExporter implements ContentEncoder {
    @Override
    public ExportResult encode(ExportRequest req) {
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
