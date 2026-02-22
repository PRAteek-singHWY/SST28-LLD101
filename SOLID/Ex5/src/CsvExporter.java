import java.nio.charset.StandardCharsets;

public class CsvExporter implements ContentEncoder {
    @Override
    public ExportResult encode(ExportRequest req) {
        String body = req.body == null ? "" : req.body;
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
