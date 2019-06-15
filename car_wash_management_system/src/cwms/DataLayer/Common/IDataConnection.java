/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwms.DataLayer.Common;

import java.sql.Connection;

/**
 *
 * @author USER
 */
public interface IDataConnection {
    Connection getConnection();
}
