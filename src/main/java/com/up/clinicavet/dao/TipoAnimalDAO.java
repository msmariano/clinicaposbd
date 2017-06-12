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

public class TipoAnimalDAO implements IGenericDAO<TipoAnimal, Integer> {

	public List<TipoAnimal> listar() throws Exception {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			List<TipoAnimal> tipoAnimal = new ArrayList<TipoAnimal>();

			con = ConnectionFactory.getConnection();

			String sql = "SELECT tipoanimal_id,nomeraca,descricao FROM TIPOANIMAL";
			statement = con.prepareStatement(sql);

			rs = statement.executeQuery();
			while (rs.next()) {
				TipoAnimal tp = new TipoAnimal();
				tp.setId(rs.getInt("tipoanimal_id"));
				tp.setNomeRaca(rs.getString("nomeraca"));
				tp.setDescricao(rs.getString("descricao"));

				tipoAnimal.add(tp);
			}

			return tipoAnimal;
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

	public TipoAnimal buscar(Integer id) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			TipoAnimal retorno = null;

			con = ConnectionFactory.getConnection();
			String sql = "SELECT tipoanimal_id,nomeraca,descricao FROM TIPOANIMAL WHERE tipoanimal_id=?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);

			rs = statement.executeQuery();
			if (rs.next()) {
				retorno = new TipoAnimal();
				retorno.setId(id);
				retorno.setNomeRaca(rs.getString("nomeraca"));
				retorno.setDescricao(rs.getString("descricao"));
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

	public TipoAnimal buscaEager(Integer id) {

		TipoAnimal tpAnimal = new TipoAnimal();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Integer idPessoa = new Integer(-1);

		try {
			con = ConnectionFactory.getConnection();

			String sql = "SELECT " + "  p.PESSOA_ID, " + "  p.CPF, " + "  p.NOME NOMEPESSOA, "
					+ "  p.NASCIMENTO NASCIMENTOPESSOA, " + "  a.ANIMAL_ID, " + "  a.TIPO_ID, " + "  a.PESSOA_ID, "
					+ "  a.NOME NOMEANIMAL, " + "  a.NASCIMENTO NASCIMENTOANIMAL, " + "  t.TIPOANIMAL_ID, "
					+ "  t.NOMERACA, " + "  t.DESCRICAO " + "FROM TIPOANIMAL t "
					+ "LEFT JOIN ANIMAL a ON a.TIPO_ID =  t.TIPOANIMAL_ID "
					+ "LEFT JOIN PESSOA p ON p.PESSOA_ID = a.PESSOA_ID "
					+ "WHERE t.TIPOANIMAL_ID = ? ORDER BY p.PESSOA_ID";

			statement = con.prepareStatement(sql);
			statement.setInt(1, id);

			rs = statement.executeQuery();
			if (rs.next()) {
				tpAnimal.setDescricao(rs.getString("DESCRICAO"));
				tpAnimal.setId(rs.getInt("TIPOANIMAL_ID"));
				tpAnimal.setNomeRaca(rs.getString("NOMERACA"));
				List<Animal> animais = new ArrayList<Animal>();
				List<Pessoa> pessoas = new ArrayList<Pessoa>();
				Pessoa p = null;

				rs.getInt("ANIMAL_ID");
				if (!rs.wasNull()) {
					do {

						Animal a = new Animal();
						a.setId(rs.getInt("ANIMAL_ID"));
						a.setNome(rs.getString("NOMEANIMAL"));
						a.setNascimento(rs.getDate("NASCIMENTOANIMAL"));
						a.setTipo(tpAnimal);

						/*if (idPessoa != rs.getInt("PESSOA_ID")) {
							if(p != null)
								p.setAnimais(animais);
							p = new Pessoa();
							p.setId(rs.getInt("PESSOA_ID"));
							p.setCpf(rs.getLong("CPF"));
							p.setNome(rs.getString("NOMEPESSOA"));
							p.setNascimento(rs.getDate("NASCIMENTOPESSOA"));
							pessoas.add(p);
							idPessoa = rs.getInt("PESSOA_ID");
						}*/
						a.setDono(p);
						animais.add(a);

					} while (rs.next());
					
					/*if(p!=null)
						p.setAnimais(animais);
					tpAnimal.setListaAnimal(animais);
					tpAnimal.setListaPessoa(pessoas);*/
				}

				return tpAnimal;
			}
		} catch (Exception e) {

		}
		return null;

	}

	public void persistir(TipoAnimal objeto) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet keys = null;
		Exception ex = null;
		try {
			con = ConnectionFactory.getConnection();

			String sql = "INSERT INTO TIPOANIMAL " + "(NOMERACA,DESCRICAO) VALUES (?,?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, objeto.getNomeRaca());
			statement.setString(2, objeto.getDescricao());

			statement.executeUpdate();
			keys = statement.getGeneratedKeys();
			if (keys.next())
				objeto.setId(keys.getInt(1));
			else
				throw new Exception("Falha ao inserir o item");
			return;
		} catch (Exception e) {
			ex = e;
		} finally {
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

	public void remover(Integer id) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "DELETE FROM TIPOANIMAL WHERE tipoanimal_id=?";
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

	public void atualizar(TipoAnimal objeto) throws Exception {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Exception ex = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "UPDATE TIPOANIMAL  set nomeraca = ?,descricao = ?   WHERE tipoanimal_id=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, objeto.getNomeRaca());
			statement.setString(2, objeto.getDescricao());
			statement.setInt(3, objeto.getId());

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
