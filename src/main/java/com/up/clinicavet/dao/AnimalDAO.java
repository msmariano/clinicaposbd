package com.up.clinicavet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.up.clinicavet.jdbc.factory.ConnectionFactory;
import com.up.clinicavet.model.Animal;
import com.up.clinicavet.model.Pessoa;
import com.up.clinicavet.model.TipoAnimal;

public class AnimalDAO implements IGenericDAO<Animal, Integer> {

	public List<Animal> listar() throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try{
			List<Animal> animais = new ArrayList<Animal>();
			
			con = ConnectionFactory.getConnection();
			
			 
			
			String sql = "SELECT animal_id,tipo_id,pessoa_id,nome,nascimento";
			statement = con.prepareStatement(sql);
			
			rs = statement.executeQuery();
			while(rs.next()){
				
				Animal animal = new Animal();
				animal.setId(rs.getInt("animal_id"));				
				Pessoa p = new Pessoa();
				p.setId(rs.getInt("pessoa_id"));
				//animal.setDono(p);				
				animal.setNome(rs.getString("nome"));
				animal.setNascimento(rs.getDate("nascimento"));
				animais.add(animal);
			}
			
			return animais;
		}catch(Exception e){
			ex = e;
		}finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
		}
		throw ex;
	}

	public Animal buscarEager(Integer id) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			Animal retorno = null;

			con = ConnectionFactory.getConnection();

			String sql = "SELECT a.nome as nomeAnimal,a.nascimento as nascimentoAnimal, "
					+ "p.PESSOA_ID,p.CPF,p.NOME as nomePessoa,p.NASCIMENTO as nascimentoPessoa, "
					+ "t.TIPOANIMAL_ID, t.NOMERACA, t.DESCRICAO as descricaoRaca " + "FROM ANIMAL a "
					+ "INNER JOIN PESSOA p on (a.pessoa_id = p.pessoa_id) "
					+ "INNER JOIN TIPOANIMAL t on (a.tipo_id = t.tipoanimal_id)" + " WHERE a.animal_id=?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);

			rs = statement.executeQuery();
			if (rs.next()) {
				retorno = new Animal();
				retorno.setId(id);
				retorno.setNome(rs.getString("nomeAnimal"));
				retorno.setNascimento(rs.getDate("nascimentoAnimal"));

				Pessoa p = new Pessoa();
				p.setId(rs.getInt("PESSOA_ID"));
				p.setCpf(rs.getLong("CPF"));
				p.setNome(rs.getString("nomePessoa"));
				p.setNascimento(rs.getDate("nascimentoPessoa"));
				//retorno.setDono(p);

				TipoAnimal ta = new TipoAnimal();
				ta.setId(rs.getInt("TIPOANIMAL_ID"));
				ta.setNomeRaca(rs.getString("NOMERACA"));
				ta.setDescricao(rs.getString("descricaoRaca"));
				//retorno.setTipo(ta);
			}

			return retorno;
		} catch (Exception e) {
			ex = e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
		}
		throw ex;
	}

	public Animal buscar(Integer id) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			Animal retorno = null;

			con = ConnectionFactory.getConnection();

			String sql = "SELECT tipo_id,pessoa_id,nome,nascimento FROM ANIMAL WHERE animal_id=?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);

			rs = statement.executeQuery();
			if (rs.next()) {
				retorno = new Animal();
				retorno.setId(id);

				Pessoa p = new Pessoa();
				p.setId(rs.getInt("pessoa_id"));
				//retorno.setDono(p);

				retorno.setNome(rs.getString("nome"));
				retorno.setNascimento(rs.getDate("nascimento"));
			}

			return retorno;
		} catch (Exception e) {
			ex = e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
		}
		throw ex;
	}

	public void persistir(Animal objeto) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet keys = null;
		Exception ex = null;
		try{
			con = ConnectionFactory.getConnection();
			
			String sql = "INSERT INTO ANIMAL "
					+ "(TIPO_ID,PESSOA_ID,NOME,NASCIMENTO) VALUES (?,?,?,?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//statement.setLong(1, objeto.getTipo().getId());
			//statement.setLong(2, objeto.getDono().getId());
			statement.setString(3, objeto.getNome());
			
			if(objeto.getNascimento() != null)
				statement.setDate(4, new java.sql.Date(objeto.getNascimento().getTime()));
			else
				statement.setDate(4, null);
			
			statement.executeUpdate();
			keys = statement.getGeneratedKeys();
			if(keys.next())
				objeto.setId(keys.getInt(1));
			else
				throw new Exception("Falha ao inserir o item");
			return;
		}catch(Exception e){
			ex = e;
		}finally{
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
		}
		throw ex;

	}

	public void remover(Integer id) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "DELETE FROM ANIMAL WHERE animal_id=?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);

			statement.execute();

		} catch (Exception e) {
			ex = e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
		}
		throw ex;

	}

	public void atualizar(Animal objeto) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "UPDATE ANIMAL set pessoa_id = ?,nome=?,nascimento =?  WHERE animal_id=?";
			statement = con.prepareStatement(sql);
			//statement.setInt(1, objeto.getDono().getId());
			statement.setString(2, objeto.getNome());
			java.sql.Date DataSql = new java.sql.Date(objeto.getNascimento().getTime());
			statement.setDate(3, DataSql);
			statement.setInt(4, objeto.getId());
			statement.execute();

		} catch (Exception e) {
			ex = e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					ex = e;
				}
			}
		}
		throw ex;

	}

}
