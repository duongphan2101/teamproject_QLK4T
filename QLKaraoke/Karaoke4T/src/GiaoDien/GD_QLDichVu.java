package GiaoDien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import connectDB.connectDB;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.border.LineBorder;

import DAO.QLDV_DAO;
import Entity.DichVu;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import java.awt.GridLayout;


public class GD_QLDichVu extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private JPanel contentPane, panel;
	private JLabel lblClock, lbltenql,TheoDoiThaoTac;
	private Timer timer;
	@SuppressWarnings("unused")
	private JButton jButton;
	private JTextField txt_MaDV;
	private JTextField txt_tenDV;
	private JTextField txt_giaDV;
	private JTextField txt_soLuong;
	private QLDV_DAO ds_dv = new QLDV_DAO();
	private JPanel panel_dsdv ;
	private boolean isSelected = false;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	String quanly;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public GD_QLDichVu() {
//		initComponents();
		String tenuser = layThongTinTen();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Dịch Vụ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1175, 650);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//goi data
		connectDB.getInstance().connect();
		loadData();
		
//		 Ho tro -----------------------
		
		TheoDoiThaoTac = new JLabel("Theo dõi thao tác");
		TheoDoiThaoTac.setIcon(new ImageIcon(GD_QLDichVu.class.getResource("/Imgs/eyes.png")));
		TheoDoiThaoTac.setForeground(Color.WHITE);
		TheoDoiThaoTac.setFont(new Font("Tahoma", Font.BOLD, 13));
		TheoDoiThaoTac.setBackground(Color.BLACK);
		TheoDoiThaoTac.setBounds(361, 30, 381, 25);
		contentPane.add(TheoDoiThaoTac);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://thach1311.github.io/huongDan/").toURI());
				}
				catch(Exception ex){}
			}
		});
		btnNewButton.setIcon(new ImageIcon(GD_Main_NV.class.getResource("/Imgs/iconHoTro.png")));
		btnNewButton.setBounds(304, 10, 49, 50);
		contentPane.add(btnNewButton);
		
//		------------------------------------------
		
		@SuppressWarnings("serial")
		JPanel left_QLDV = new JPanel() {
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		left_QLDV.setBackground(new Color(255, 255, 255, 180));
		left_QLDV.setBounds(0, 148, 294, 465);
		left_QLDV.setOpaque(false);
		contentPane.add(left_QLDV);
		left_QLDV.setLayout(null);
		
		txt_MaDV = new JTextField();
		txt_MaDV.setEnabled(false);
		txt_MaDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_MaDV.setBounds(10, 41, 150, 30);
		txt_MaDV.setBorder(null);
		left_QLDV.add(txt_MaDV);
		txt_MaDV.setColumns(10);
		
		JLabel lblmaDV = new JLabel("Mã Dịch Vụ");
		lblmaDV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblmaDV.setBounds(10, 27, 85, 13);
		left_QLDV.add(lblmaDV);
		
		txt_tenDV = new JTextField();
		txt_tenDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_tenDV.setColumns(10);
		txt_tenDV.setBorder(null);
		txt_tenDV.setBounds(10, 126, 230, 30);
		left_QLDV.add(txt_tenDV);
		
		JLabel lbltenDV = new JLabel("Tên Dịch Vụ");
		lbltenDV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltenDV.setBounds(10, 112, 85, 13);
		left_QLDV.add(lbltenDV);
		
		txt_giaDV = new JTextField();
		txt_giaDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_giaDV.setColumns(10);
		txt_giaDV.setBorder(null);
		txt_giaDV.setBounds(10, 209, 230, 30);
		left_QLDV.add(txt_giaDV);
		
		JLabel lblgiaDV = new JLabel("Giá Dịch Vụ");
		lblgiaDV.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblgiaDV.setBounds(10, 195, 85, 13);
		left_QLDV.add(lblgiaDV);
		
		txt_soLuong = new JTextField();
		txt_soLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_soLuong.setColumns(10);
		txt_soLuong.setBorder(null);
		txt_soLuong.setBounds(10, 299, 121, 30);
		left_QLDV.add(txt_soLuong);
		
		JLabel lblchuthich = new JLabel("Số lượng");
		lblchuthich.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblchuthich.setBounds(10, 285, 85, 13);
		left_QLDV.add(lblchuthich);
		
		testbutton.Buttontest btnthem = new testbutton.Buttontest();
		btnthem.setText("Thêm");
		btnthem.setForeground(new Color(255, 255, 255));
		btnthem.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnthem.setBackground(new Color(90, 167, 167));
		btnthem.setBounds(10, 359, 100, 48);
		btnthem.setRippleColor(new Color(255, 255, 255));
		btnthem.setShadowColor(new Color(0,0,0));
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnthemActionPerformed(e);
				 reloadDichVu();
				 loadData();
			}
		});
		loadData();
		
		left_QLDV.add(btnthem);
		testbutton.Buttontest btnsua = new testbutton.Buttontest();
		btnsua.setText("Sửa");
		btnsua.setForeground(Color.WHITE);
		btnsua.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnsua.setBackground(new Color(226, 211, 107));
		btnsua.setBounds(164, 359, 100, 48);
		btnsua.setRippleColor(new Color(255, 255, 255));
		btnsua.setShadowColor(new Color(0,0,0));
		
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnsuaActionPerformed(e);
			}
		});
		left_QLDV.add(btnsua);
		
		testbutton.Buttontest btnlammoi = new testbutton.Buttontest();
		btnlammoi.setText("Làm mới");
		btnlammoi.setForeground(Color.WHITE);
		btnlammoi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnlammoi.setBackground(new Color(51, 83, 158));
		btnlammoi.setBounds(164, 413, 100, 48);
		btnlammoi.setRippleColor(new Color(255, 255, 255));
		btnlammoi.setShadowColor(new Color(0,0,0));
		
		btnlammoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnlammoiActionPerformed(e);
			}
		});
		left_QLDV.add(btnlammoi);
		
		testbutton.Buttontest btnxoa = new testbutton.Buttontest();
		btnxoa.setText("Xóa");
		btnxoa.setForeground(Color.WHITE);
		btnxoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnxoa.setBackground(new Color(254, 122, 21));
		btnxoa.setBounds(10, 413, 100, 48);
		btnxoa.setRippleColor(new Color(255, 255, 255));
		btnxoa.setShadowColor(new Color(0,0,0));
		
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maDV = txt_MaDV.getText();
				xoaDichVu(maDV);
				reloadDichVu();
				loadData();
				JOptionPane.showMessageDialog(null,"Xóa thành công");
			}
		});
		left_QLDV.add(btnxoa);
		
		JButton jButton_1 = new JButton("Đăng Xuất");
		jButton_1.setBounds(990, 10, 150, 50);
		jButton_1.setFont(new Font("Tahoma ", Font.BOLD, 14));
		jButton_1.setBackground(new Color(255, 0, 0));
		jButton_1.setForeground(Color.WHITE);
		
			jButton_1.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
			jButton_1.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
			jButton_1.setContentAreaFilled(false);
			jButton_1.setFocusPainted(false);
			jButton_1.setOpaque(true);
			contentPane.add(jButton_1);
			
					jButton_1.addMouseListener(new MouseAdapter() {
					    @Override
					    public void mouseEntered(MouseEvent e) {
					        jButton_1.setBackground(Color.BLACK);
					    }
			
					    @Override
					    public void mouseExited(MouseEvent e) {
					        jButton_1.setBackground(new Color(255, 0, 0));
					    }
					});
					
							jButton_1.addActionListener(new ActionListener() {
							    public void actionPerformed(ActionEvent e) {
							        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất!", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							            GD_Login lg = new GD_Login();
							            lg.setVisible(true);
							            lg.setLocationRelativeTo(null);
							            dispose();
							        }
							    }
							});

		JPanel box_clock = new JPanel();
        box_clock.setBounds(34, 10, 260, 50);
        box_clock.setBorder(new LineBorder(Color.BLACK));
        contentPane.add(box_clock);
        box_clock.setLayout(null);

        lblClock = new JLabel();
        lblClock.setHorizontalAlignment(SwingConstants.CENTER);
        lblClock.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblClock.setBounds(0, 0, 260, 50);
        lblClock.setOpaque(true);
        lblClock.setBackground(Color.WHITE);
        box_clock.add(lblClock);

        timer = new Timer(0, this);
        timer.start();
		
        testbutton.Buttontest btnphonghat = new testbutton.Buttontest();
        btnphonghat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_PhongHat gdphong = new GD_PhongHat();
				gdphong.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokePhongHat = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        btnphonghat.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokePhongHat, "F1");
        btnphonghat.getActionMap().put("F1", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_PhongHat gdphong = new GD_PhongHat();
				gdphong.setVisible(true);
				dispose();
            }
        });
        btnphonghat.setBorder(null);
        btnphonghat.setText("Phòng Hát (F1)");
        btnphonghat.setForeground(Color.WHITE);
        btnphonghat.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnphonghat.setBackground(new Color(0, 0, 0, 150));
        btnphonghat.setBounds(0, 70, 232, 87);
		contentPane.add(btnphonghat);
		btnphonghat.setLayout(null);
		
		testbutton.Buttontest btndichvu = new testbutton.Buttontest();
        btndichvu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
        btndichvu.setBorder(null);
        btndichvu.setText("Dịch Vụ (F2)");
        btndichvu.setForeground(Color.WHITE);
        btndichvu.setFont(new Font("Tahoma", Font.BOLD, 20));
        btndichvu.setBackground(new Color(128, 128,128, 150));
        btndichvu.setBounds(230, 70, 239, 87);
		contentPane.add(btndichvu);
		btndichvu.setLayout(null);
		
		testbutton.Buttontest btnnhanvien = new testbutton.Buttontest();
        btnnhanvien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_QLNhanVien gdnv = new GD_QLNhanVien();
				gdnv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdnv.setVisible(true);
				dispose();
			}
		});
        KeyStroke keyStrokeNV = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
        btnnhanvien.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeNV, "F3");
        btnnhanvien.getActionMap().put("F3", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_QLNhanVien gdnv = new GD_QLNhanVien();
				gdnv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdnv.setVisible(true);
				dispose();
            }
        });
        btnnhanvien.setBorder(null);
        btnnhanvien.setText("Nhân Viên (F3)");
        btnnhanvien.setForeground(Color.WHITE);
        btnnhanvien.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnnhanvien.setBackground(new Color(0, 0, 0, 150));
        btnnhanvien.setBounds(465, 70, 232, 87);
		contentPane.add(btnnhanvien);
		btnnhanvien.setLayout(null);
		
		testbutton.Buttontest btntaikhoan = new testbutton.Buttontest();
        btntaikhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_TaiKhoan gdtaikhoan = new GD_TaiKhoan();
				gdtaikhoan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtaikhoan.setVisible(true);
				dispose();

			}
		});
        KeyStroke keyStroketk = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
        btntaikhoan.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroketk, "F4");
        btntaikhoan.getActionMap().put("F4", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_TaiKhoan gdtaikhoan = new GD_TaiKhoan();
				gdtaikhoan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtaikhoan.setVisible(true);
				dispose();
            }
        });
        btntaikhoan.setBorder(null);
        btntaikhoan.setText("Tài Khoản (F4)");
        btntaikhoan.setForeground(Color.WHITE);
        btntaikhoan.setFont(new Font("Tahoma", Font.BOLD, 20));
        btntaikhoan.setBackground(new Color(0, 0, 0, 150));
        btntaikhoan.setBounds(695, 70, 232, 87);
		contentPane.add(btntaikhoan);
		btntaikhoan.setLayout(null);
		
		testbutton.Buttontest btnthongke = new testbutton.Buttontest();
        btnthongke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GD_ThongKeHoaDon gdtkhd = new GD_ThongKeHoaDon();
				gdtkhd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtkhd.setVisible(true);
				dispose();

			}
		});
        KeyStroke keyStroketke = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        btnthongke.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroketke, "F5");
        btnthongke.getActionMap().put("F5", new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi phím tắt được nhấn
				GD_ThongKeHoaDon gdtkhd = new GD_ThongKeHoaDon();
				gdtkhd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtkhd.setVisible(true);
				dispose();
            }
        });
        btnthongke.setBorder(null);
        btnthongke.setText("Thống Kê (F5)");
        btnthongke.setForeground(Color.WHITE);
        btnthongke.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnthongke.setBackground(new Color(0, 0, 0, 150));
        btnthongke.setBounds(925, 70, 232, 87);
		contentPane.add(btnthongke);
		btnthongke.setLayout(null);
		
		JLabel lblquanly = new JLabel("QL:");
		lblquanly.setForeground(Color.WHITE);
		lblquanly.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblquanly.setBounds(878, -20, 232, 80);
		contentPane.add(lblquanly);
		
		lbltenql = new JLabel();
		lbltenql.setForeground(Color.WHITE);
		lbltenql.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltenql.setBounds(853, 6, 232, 80);
		lbltenql.setText(tenuser);
		contentPane.add(lbltenql);
		
		JLabel lblavatar = new JLabel("");
		lblavatar.setHorizontalAlignment(SwingConstants.CENTER);
		lblavatar.setIcon(new ImageIcon(GD_TaiKhoan.class.getResource("/Imgs/t1 1.png")));
		lblavatar.setBounds(90, -444, 1333, 957);
		contentPane.add(lblavatar);
	
		JLabel lblhinhnen = new JLabel("");
		lblhinhnen.setHorizontalAlignment(SwingConstants.CENTER);
		lblhinhnen.setIcon(new ImageIcon(GD_Main_NV.class.getResource("/Imgs/370.png")));
		lblhinhnen.setBounds(-95, -176, 1333, 957);
		contentPane.add(lblhinhnen);
		
		
		
	}
	
	private String layThongTinTen() {
	    String query = "SELECT tenNV FROM TaiKhoan WHERE maTK = ?";
	    try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=Karaoke4T;user=sa;password=123");
	        pst = con.prepareStatement(query);
	        pst.setString(1, "TK001"); // Điền điều kiện truy vấn của bạn
	        rs = pst.executeQuery();
	        if (rs.next()) {
	            return rs.getString("tenNV");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	private DichVu reverSPFromTextField() {
		String maDV = txt_MaDV.getText();
		double giaDV = Double.parseDouble(txt_giaDV.getText());
		int soLuong = Integer.parseInt(txt_soLuong.getText());
		String tenDV = txt_tenDV.getText();
		DichVu d = new DichVu(maDV, giaDV, soLuong, tenDV);
		return d;
		
	}
	
	protected void btnxoaActionPerformed(ActionEvent e) {
//		ds_dv.deleteDvbymaDV(txt_MaDV.getText());
//		refresh();
			
	}
	protected void btnlammoiActionPerformed(ActionEvent e) {
		refresh();
		
	}
	protected void btnsuaActionPerformed(ActionEvent e) {
		DichVu ph = reverSPFromTextField();
		ds_dv.update(ph);
		JOptionPane.showMessageDialog(null, "Sửa thành công!");
		theoDoiThaoTac("Bạn vừa mới sửa dịch vụ: ",ph.getMaDichVu());
		loadData();
	}
	protected void btnthemActionPerformed(ActionEvent e) {
	    // Check if the service name is empty
	    if (txt_tenDV.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập tên dịch vụ trước khi thêm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the execution if the service name is empty
	    }
	    if (txt_tenDV.getText().matches(".*[!@#$%^&*(){}>?<_=+-].*")) {
	        JOptionPane.showMessageDialog(this, "Tên dịch vụ không được chứa ký tự đặc biệt.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the execution if the name contains special characters
	    }

	    // Check if the service price is empty
	    if (txt_giaDV.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập giá dịch vụ trước khi thêm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the execution if the service price is empty
	    }
	    // Additional validation for the price (numeric and non-negative validation)
	    try {
	        double giaDV = Double.parseDouble(txt_giaDV.getText().trim());

	        // Check if the price is non-negative
	        if (giaDV < 0) {
	            JOptionPane.showMessageDialog(this, "Giá dịch vụ không được là số âm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return; // Stop the execution if the price is negative
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập giá dịch vụ là một số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the execution if the price is not a valid number
	    }
	    
	    // Check if the quantity is empty
	    if (txt_soLuong.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng trước khi thêm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the execution if the quantity is empty
	    }

	    // Additional validation for the quantity (numeric and non-negative validation)
	    try {
	        int soLuong = Integer.parseInt(txt_soLuong.getText().trim());

	        // Check if the quantity is non-negative
	        if (soLuong < 0) {
	            JOptionPane.showMessageDialog(this, "Số lượng không được là số âm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return; // Stop the execution if the quantity is negative
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng là một số hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the execution if the quantity is not a valid number
	    }

	    // Check if the quantity contains special characters
	    if (!txt_soLuong.getText().matches("^[0-9]+$")) {
	        JOptionPane.showMessageDialog(this, "Số lượng không được chứa ký tự đặc biệt.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return; // Stop the execution if the quantity contains special characters
	    }
	    
		QLDV_DAO dsdv = new QLDV_DAO();
		int maxMaDV = dsdv.getMaxMaDV();
	    
	    // Tăng mã dv lên 1 để có mã mới
		maxMaDV++;
	    
	    // Gán giá trị mới cho ô nhập liệu mã dv
	    txt_MaDV.setText("DV" + String.format("%03d", maxMaDV));
		DichVu dv = reverSPFromTextField();
		dsdv.themDichVu(dv);
		JOptionPane.showMessageDialog(this,"Thêm Dịch Vụ Thành Công");
		theoDoiThaoTac("Bạn vừa mới thêm dịch vụ: ",txt_MaDV.getText());
		refresh();
	}
	
	public void refresh() {
		txt_MaDV.setText("");
		txt_giaDV.setText("");
		txt_soLuong.setText("");
		txt_tenDV.setText("");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
            // Cập nhật thời gian
            updateClock();
        }
	}
	
	private void loadData() {
		QLDV_DAO ds = new QLDV_DAO();
		ArrayList<DichVu> listDV = ds.docbang();
		draw(listDV);
	}
	
	private void draw(ArrayList<DichVu> listdv) {
		JPanel Right_QLDV = new JPanel();
		Right_QLDV.setBorder(null);
		Right_QLDV.setBackground(new Color(255, 255, 255, 150));
		Right_QLDV.setBounds(291, 148, 887, 465);
		contentPane.add(Right_QLDV);
		Right_QLDV.setLayout(null);
		
		JScrollPane scrollPane_DSDV = new JScrollPane();
		scrollPane_DSDV.setBounds(0, 0, 870, 465);
		scrollPane_DSDV.setBackground(new Color(255, 255, 255, 0));
		Right_QLDV.add(scrollPane_DSDV);
		
		panel_dsdv = new JPanel();
		panel_dsdv.setBackground(new Color(255, 255, 255));
		scrollPane_DSDV.setViewportView(panel_dsdv);
		panel_dsdv.setLayout(new GridLayout(0, 5, 10, 10));
    	for(DichVu dv : listdv) {
    		//load label cha
    		JPanel pnl_dichvu = new JPanel();
    		pnl_dichvu.setBackground(new Color(255, 255, 255));
    		pnl_dichvu.setLayout(null);
    		pnl_dichvu.setPreferredSize(new Dimension(100,100));
    		pnl_dichvu.setBorder(LineBorder.createBlackLineBorder());
    		panel_dsdv.add(pnl_dichvu);


    		
    		//load label gia
    		JLabel lbl_price = new JLabel(dv.getGiaDichVu() + "/1 cái");
    		lbl_price.setHorizontalAlignment(SwingConstants.CENTER);
    		lbl_price.setFont(new Font("Tahoma", Font.BOLD, 11));
//    		lbl_price.setBorder(new LineBorder(new Color(0, 0, 0)));
    		lbl_price.setBounds(39, 115, 85, 35);
    		pnl_dichvu.add(lbl_price);
    		
    		//load ten dich vu
    		JLabel lbl_name = new JLabel(dv.getTenDichVu());
    		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 13));
    		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
    		lbl_name.setBounds(39, 98, 85, 35);
    		pnl_dichvu.add(lbl_name);
    		
    		//load hinh anh
    		JLabel lbl_hinhanh = new JLabel("");
    		lbl_hinhanh.setIcon(new ImageIcon(GD_QLDichVu.class.getResource("/Imgs/food.png")));
    		lbl_hinhanh.setHorizontalAlignment(SwingConstants.CENTER);
    		lbl_hinhanh.setBounds(0, 0, 162, 121);
    		pnl_dichvu.add(lbl_hinhanh);
    		
    		LineBorder labelBorder = new LineBorder(Color.black, 5);
    		pnl_dichvu.setBorder(labelBorder);
    		
    		pnl_dichvu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					txt_MaDV.setText(dv.getMaDichVu());
					txt_tenDV.setText(dv.getTenDichVu());
					txt_giaDV.setText(dv.getGiaDichVu()+"");
					txt_soLuong.setText(dv.getSoLuongDichVu()+"");
					
					
					isSelected = !isSelected;
					if (isSelected) {
						LineBorder labelBorder = new LineBorder(Color.blue, 5);
						pnl_dichvu.setBorder(labelBorder);
					}
					else {
						LineBorder labelBorder = new LineBorder(Color.black, 5);
						pnl_dichvu.setBorder(labelBorder);
					}
				}
			});
    		
		}
    }
	
//	private void initComponents() {
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            public void windowClosing(java.awt.event.WindowEvent evt) {
//                formWindowClosing(evt);
//            }
//        });
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 400, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 300, Short.MAX_VALUE)
//        );
//
//        pack();
//    }

    @SuppressWarnings("unused")
	private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         GD_Main_QL mainql=new GD_Main_QL();
         mainql.setVisible(true);
    }
	
	private void theoDoiThaoTac(String thaotac,String dulieu) {
		   TheoDoiThaoTac.setText(thaotac +" "+ dulieu);
	}
	
	//dong ho
    private void updateClock() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        
        String ampm;
        if (hour >= 12) {
            ampm = "PM";
            if (hour > 24) {
                hour -= 12;
            }
        } else {
            ampm = "AM";
            if (hour == 0) {
                hour = 12;
            }
        }
        
        String time = String.format("%02d:%02d:%02d %s  %02d/%02d/%04d", hour, minute, second, ampm, day, month, year);
        lblClock.setText(time);
    }

    private void reloadDichVu() {
        // Clear existing components in the panel
    	panel_dsdv.removeAll();
    	panel_dsdv.revalidate();
        // Reload data from the DAO
        QLDV_DAO ds = new QLDV_DAO();
        ArrayList<DichVu> listDV = ds.docbang();

        // Redraw the panel with the updated data
        draw(listDV);

    }

    public void xoaDichVu(String maDV) {
		// Thông tin kết nối đến cơ sở dữ liệu
		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
		String username = "sa";
		String password = "123";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);

			// Thực hiện câu lệnh DELETE
			String sql = "delete from DichVu where maDV = ?";
			PreparedStatement statement = connection.prepareStatement(sql);

//	            System.out.print("Lay madh cho chi tiet hd" + layMaHDChoChiTiet);

			// Thiết lập giá trị tham số
			statement.setString(1, maDV);

			// Thực hiện câu lệnh DELETE
			statement.executeUpdate();

			// Đóng các tài nguyên
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

