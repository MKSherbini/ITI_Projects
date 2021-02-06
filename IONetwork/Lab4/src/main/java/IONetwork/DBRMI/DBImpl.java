package IONetwork.DBRMI;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static IONetwork.DBRMI.DSManager.loadDSFile;

public class DBImpl extends UnicastRemoteObject
        implements DBInt {


    protected DBImpl() throws RemoteException {
    }

    protected DBImpl(int port) throws RemoteException {
        super(port);
    }

    protected DBImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void AddEmp(int id, String name) {
        try (Connection con = loadDSFile().getConnection();
             PreparedStatement pstmt = con.prepareStatement("insert into employee (ID,F_Name) VALUES (?,?);");
        ) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            var rs = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void UpdateEmp(int id, String newName) {
        try (Connection con = loadDSFile().getConnection();
             PreparedStatement pstmt = con.prepareStatement("update employee set F_Name=? where ID=?;");
        ) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            var rs = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void DeleteEmp(int id) {
        try (Connection con = loadDSFile().getConnection();
             PreparedStatement pstmt = con.prepareStatement("delete from employee where ID=?;");
        ) {
            pstmt.setInt(1, id);
            var rs = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Emp getEmp(int id) {
        try (Connection con = loadDSFile().getConnection();
             PreparedStatement pstmt = con.prepareStatement("select ID,F_Name from employee where ID=?;");
        ) {
            pstmt.setInt(1, id);
            var rs = pstmt.executeQuery();

            if (rs.next())
                return new Emp(rs.getInt(1), rs.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emp> getEmpList() {
        var emps = new ArrayList<Emp>();
        try (Connection con = loadDSFile().getConnection();
             PreparedStatement pstmt = con.prepareStatement("select ID,F_Name from employee;");
        ) {
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                emps.add(new Emp(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return emps;
    }
}