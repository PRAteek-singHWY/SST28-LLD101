public class Exporter {
    private final ContentEncoder encoder;
    private final RequestFilter filter;

    public Exporter(ContentEncoder encoder) {
        this(encoder, req -> req);
    }

    public Exporter(ContentEncoder encoder, RequestFilter filter) {
        this.encoder = encoder;
        this.filter = filter;
    }

    public ExportResult export(ExportRequest req) {
        if (req == null)
            throw new IllegalArgumentException("Request cannot be null");

        ExportRequest processed = filter.filter(req);
        return encoder.encode(processed);
    }
}
