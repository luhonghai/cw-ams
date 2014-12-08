package uk.ac.gre.cw.aircraft.hanlder;

import java.util.ArrayList;
import java.util.Collection;

public class TableData<T> {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public static class Column {
        private String title;
        private String data;
        private String defaultContent = "";

        public Column() {

        }

        public Column(String title, String data) {
            this.title = title;
            this.data = data;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getDefaultContent() {
            return defaultContent;
        }

        public void setDefaultContent(String defaultContent) {
            this.defaultContent = defaultContent;
        }
    }

    private Collection<T> data;

    private T object;

    private Collection<Column> columns;

    private String message;

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public Collection<Column> getColumns() {
        return columns;
    }

    public void setColumns(Collection<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Column column) {
        if (columns == null) columns = new ArrayList<Column>();
        columns.add(column);
    }
}
