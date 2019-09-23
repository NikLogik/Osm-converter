package ru.niklogik.io;

import org.matsim.core.api.internal.MatsimSomeWriter;
import org.matsim.core.utils.io.MatsimXmlWriter;
import ru.niklogik.osm.OsmData;

import java.io.IOException;

public abstract class AttractionObjectWriter extends MatsimXmlWriter implements MatsimSomeWriter {

    protected OsmData data;

    public AttractionObjectWriter(OsmData data) {
        this.data = data;
    }

    public abstract void write() throws IOException;
}
