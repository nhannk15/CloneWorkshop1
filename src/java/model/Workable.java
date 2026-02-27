package model;

import java.sql.SQLException;
import java.util.List;

public interface Workable<G> {
    public int insert(G g) throws ClassNotFoundException, SQLException;
    public void listAll() throws ClassNotFoundException, SQLException;
    public int update(G g) throws ClassNotFoundException, SQLException;
    public int delete(String id) throws ClassNotFoundException, SQLException;
    public G getObjectById(String id) throws ClassNotFoundException, SQLException;
}
