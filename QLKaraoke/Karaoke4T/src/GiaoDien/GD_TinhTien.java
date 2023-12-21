package GiaoDien;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Entity.CustomDashedLineSeparator;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import javax.swing.UIManager;

import testbutton.Buttontest;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DecimalFormat;
import javax.swing.SwingConstants;

@SuppressWarnings("unused")
public class GD_TinhTien extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Double VAT = 0.08;
	JTextField txt_tienNhan;
	JLabel lbl_tenPhongTT, lbl_sdtKH,lbl_tenKH,lbl_tenNV, lbl_gioNhanPhong, lbl_gioTraPhong;
	JLabel lbl_tongThoiLuong, lbl_tienDichVu, lbl_tienPhong, lbl_tongCong, lbl_tienThua ,lbl_tongTam,lbl_VAT;
	JCheckBox cbox_xuatHoaDon;
	String maHD, layMaPhong;
	CustomDashedLineSeparator custom;
	@SuppressWarnings("unused")
	private String khuyenMai = "5%";
	private Border defaultBorder;
	private JLabel lbGiaTriKhuyenMai;
	private JLabel lblNewLabel_1;
	private String laySDT;
	private JTable table = new JTable();
	private DefaultTableModel model = (DefaultTableModel) table.getModel();
	DecimalFormat currencyFormat = new DecimalFormat("###,###,### VND");
	private JLabel lblNewLabel;
	private JLabel valueTenPhong;
	/**
	 * Launch the application.
	 */
	
	
	/**
	 * Create the frame.
	 */
	public GD_TinhTien() {  
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 711);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnl_txt_tinhTien = new JPanel();
		pnl_txt_tinhTien.setBackground(new Color(0, 88, 176));
		pnl_txt_tinhTien.setBounds(0, 0, 806, 96);
		contentPane.add(pnl_txt_tinhTien);
		pnl_txt_tinhTien.setLayout(null);
		
		lbl_tenPhongTT = new JLabel("TÍNH TIỀN");
		lbl_tenPhongTT.setForeground(new Color(255, 255, 255));
		lbl_tenPhongTT.setBounds(271, 25, 276, 49);
		pnl_txt_tinhTien.add(lbl_tenPhongTT);
		lbl_tenPhongTT.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		lblNewLabel_1 = new JLabel("SĐT Khách :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(61, 107, 700, 20);
		contentPane.add(lblNewLabel_1);
		
		lbl_sdtKH = new JLabel("0123456789");
		lbl_sdtKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_sdtKH.setBounds(161, 107, 600, 20);
		contentPane.add(lbl_sdtKH);
		
		JLabel lblNewLabel_1_2 = new JLabel("Họ tên KH :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(61, 152, 700, 20);
		contentPane.add(lblNewLabel_1_2);
		
		lbl_tenKH = new JLabel("Trương Đại Lộc");
		lbl_tenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tenKH.setBounds(161, 152, 600, 20);
		contentPane.add(lbl_tenKH);
		
		JLabel lblNewLabel_1_3 = new JLabel("Họ tên NV:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(61, 199, 216, 20);
		contentPane.add(lblNewLabel_1_3);
		
		lbl_tenNV = new JLabel("Nguyễn Văn C");
		lbl_tenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tenNV.setBounds(161, 199, 146, 20);
		contentPane.add(lbl_tenNV);
		
		JLabel lblNewLabel_1_4 = new JLabel("Giờ nhận phòng :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(416, 107, 345, 20);
		contentPane.add(lblNewLabel_1_4);
		
		lbl_gioNhanPhong = new JLabel("17:30");
		lbl_gioNhanPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_gioNhanPhong.setBounds(545, 107, 216, 20);
		contentPane.add(lbl_gioNhanPhong);
		
		JLabel lblNewLabel_1_5 = new JLabel("Giờ trả phòng :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_5.setBounds(416, 152, 345, 20);
		contentPane.add(lblNewLabel_1_5);
		
		lbl_gioTraPhong = new JLabel("18:30");
		lbl_gioTraPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_gioTraPhong.setBounds(534, 152, 227, 20);
		contentPane.add(lbl_gioTraPhong);
		
		JLabel lblNewLabel_1_6 = new JLabel("Tổng thời lượng :");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_6.setBounds(545, 199, 199, 20);
		contentPane.add(lblNewLabel_1_6);
		
		lbl_tongThoiLuong = new JLabel("60 phút");
		lbl_tongThoiLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tongThoiLuong.setBounds(680, 199, 58, 20);
		contentPane.add(lbl_tongThoiLuong);
		
		JLabel lblNewLabel_1_7 = new JLabel("Tiền dịch vụ :");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7.setBounds(416, 413, 129, 20);
		contentPane.add(lblNewLabel_1_7);
		
		lbl_tienDichVu = new JLabel("75.000 VND");
		lbl_tienDichVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tienDichVu.setBounds(523, 413, 194, 20);
		contentPane.add(lbl_tienDichVu);
		
		JLabel lblNewLabel_1_8 = new JLabel("Tiền phòng :");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_8.setBounds(416, 455, 97, 20);
		contentPane.add(lblNewLabel_1_8);
		
		lbl_tienPhong = new JLabel("200.000 VND");
		lbl_tienPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tienPhong.setBounds(523, 455, 217, 20);
		contentPane.add(lbl_tienPhong);
		
		JLabel lblNewLabel_1_9 = new JLabel("Tổng cộng :");
		lblNewLabel_1_9.setForeground(new Color(255, 0, 0));
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9.setBounds(61, 535, 97, 20);
		contentPane.add(lblNewLabel_1_9);
		
		lbl_tongCong = new JLabel("275.000.000");
		lbl_tongCong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tongCong.setBounds(168, 535, 122, 20);
		contentPane.add(lbl_tongCong);
		
		table = new JTable();
		table.setBounds(41, 463, 729, -179);
		contentPane.add(table);
		
		JLabel lblNewLabel_1_7_1 = new JLabel("Tiền nhận :");
		lblNewLabel_1_7_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7_1.setBounds(61, 413, 91, 20);
		contentPane.add(lblNewLabel_1_7_1);
		
		JLabel lblNewLabel_1_7_2 = new JLabel("Tiền thừa :");
		lblNewLabel_1_7_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7_2.setBounds(61, 473, 97, 20);
		contentPane.add(lblNewLabel_1_7_2);
		
		txt_tienNhan = new JTextField();
		txt_tienNhan.setBounds(161, 410, 129, 31);
		txt_tienNhan.setBorder(defaultBorder);
		contentPane.add(txt_tienNhan);
		txt_tienNhan.setColumns(10);
		txt_tienNhan.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				kiemTraTienNhan();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				kiemTraTienNhan();
			}
		});
		txt_tienNhan.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	kiemTraTienNhan();
		    }
		});
		testbutton.Buttontest quayLai = new testbutton.Buttontest();
		quayLai.setBorder(null);
		quayLai.setText("Quay lại");
		quayLai.setForeground(Color.WHITE);
		quayLai.setFont(new Font("Tahoma", Font.BOLD, 13));
		quayLai.setBackground(new Color(0, 128, 255));
		quayLai.setBounds(78, 591, 115, 41);
		quayLai.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				GD_TraPhong gdtraphong = new GD_TraPhong();
				gdtraphong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gdtraphong.setVisible(true);
				dispose();
			}
		});
	    contentPane.add(quayLai);
	    quayLai.setLayout(null);
	    
	    lbl_tienThua = new JLabel("");
	    lbl_tienThua.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lbl_tienThua.setBounds(161, 468, 129, 36);
	    contentPane.add(lbl_tienThua);
	    
	    JLabel lblNewLabel_1_8_1 = new JLabel("VAT :");
	    lblNewLabel_1_8_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel_1_8_1.setBounds(416, 566, 97, 20);
	    contentPane.add(lblNewLabel_1_8_1);
	    
	    lbl_VAT = new JLabel("8%");
	    lbl_VAT.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lbl_VAT.setBounds(474, 566, 217, 20);
	    contentPane.add(lbl_VAT);
	    
	    lbl_tongTam = new JLabel("275.000 VND");
	    lbl_tongTam.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lbl_tongTam.setBounds(523, 494, 204, 20);
	    contentPane.add(lbl_tongTam);
	    
	    JLabel lblNewLabel_1_9_1 = new JLabel("Tổng tạm :");
	    lblNewLabel_1_9_1.setForeground(new Color(0, 0, 0));
	    lblNewLabel_1_9_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblNewLabel_1_9_1.setBounds(416, 494, 97, 20);
	    contentPane.add(lblNewLabel_1_9_1);
	    
	    JLabel lbKhuyenMai = new JLabel("Khuyến mãi:");
	    lbKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lbKhuyenMai.setBounds(416, 529, 97, 20);
	    contentPane.add(lbKhuyenMai);
	    
	    lbGiaTriKhuyenMai = new JLabel("10%");
	    lbGiaTriKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lbGiaTriKhuyenMai.setBounds(523, 525, 97, 20);
	    contentPane.add(lbGiaTriKhuyenMai);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setBounds(26, 229, 714, 171);
	    contentPane.add(scrollPane);
	    
	    table = new JTable();
	    scrollPane.setViewportView(table);
	    
	    model.addColumn("Tên Dịch vụ");
	    model.addColumn("Giá Dịch vụ");
	    model.addColumn("Số Lượng");
	    table.setModel(model);
	    
	    Buttontest btntstThanhTon = new Buttontest();
	    btntstThanhTon.setText("THANH TOÁN");
	    btntstThanhTon.setForeground(Color.WHITE);
	    btntstThanhTon.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btntstThanhTon.setBorder(null);
	    btntstThanhTon.setBackground(new Color(255, 60, 60));
	    btntstThanhTon.setBounds(545, 591, 115, 48);
	    btntstThanhTon.addActionListener(new ActionListener() {
	    	private void layTienThua() {
	    	    String tienKhachTra = txt_tienNhan.getText();
	    	    String tc = ((String) lbl_tongCong.getText()).replace(",", "");
	            updateHoaDon(maHD,lbl_gioTraPhong.getText(),tc,tienKhachTra);
	    	    try {	    	    	
	    	        Double tienThua = Double.parseDouble(tienKhachTra) - Double.parseDouble(tc);
	    	        

	    	        String tienThuaFormatted = currencyFormat.format(tienThua);
	    	        if (tienThua > 0) {
//	    	            lbl_tienThua.setText(tienThua.toString() + " VND");
	    	        	lbl_tienThua.setText(tienThuaFormatted);
	    	        }else if(tienThua == 0) {
	    	        	lbl_tienThua.setText(tienThuaFormatted);
	    	        }else {
	    	            JOptionPane.showMessageDialog(contentPane, "Số tiền khách đưa không đủ! Yêu cầu trả đủ");
	    	        }
	    	    } catch (NumberFormatException e) {
	    	        JOptionPane.showMessageDialog(contentPane, "Nhập không hợp lệ. Vui lòng nhập số tiền đúng định dạng.");
	    	    }
	    	}
	    	
            private void printToPDF() {
            	Document document = new Document();
                try {

                    // Sử dụng thời gian hiện tại để tạo tên file duy nhất
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String fileName = "PDF/InHoaDon_" + dateFormat.format(new Date()) + ".pdf";

                    PdfWriter.getInstance(document, new FileOutputStream(fileName));
                    document.open();

                    BaseFont unicodeFont = BaseFont.createFont("Tahoma Regular font.ttf", BaseFont.IDENTITY_H,
                            BaseFont.EMBEDDED);
                    com.itextpdf.text.Font vietnameseFont = new com.itextpdf.text.Font(unicodeFont, 12,
                            com.itextpdf.text.Font.NORMAL);

                    // Add content to the PDF document
                    addContent(document, vietnameseFont);

                    // Close the document
                    document.close();
                    
                    // Mở file PDF sau khi đã lưu
                    Desktop.getDesktop().open(new File(fileName));
                    
                    // Display a message indicating successful PDF creation
                    JOptionPane.showMessageDialog(contentPane, "ĐÃ XUẤT FILE PDF!", "Success",
                            JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
			private void addContent(Document document, com.itextpdf.text.Font vietnameseFont) throws DocumentException {
				// TODO Auto-generated method stub
				// Add content to the PDF document\
				// ngày lập đơn được đặt ở gốc bên phải trên
				PdfPTable infoTable = new PdfPTable(1);
			    infoTable.setWidthPercentage(100);

			    // Thêm ô ngày lập đơn vào bảng
			    PdfPCell dateCell = new PdfPCell(new Paragraph("DATE:" + getCurrentDate(), vietnameseFont));
			    dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			    dateCell.setBorder(Rectangle.NO_BORDER);
			    infoTable.addCell(dateCell);
			    
			    // Thêm ô giờ và phút vào bảng
			    PdfPCell timeCell = new PdfPCell(new Paragraph("TIME:" + getCurrentTime(), vietnameseFont));
			    timeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			    timeCell.setBorder(Rectangle.NO_BORDER);
			    infoTable.addCell(timeCell);
			    
			    // Thêm bảng vào tài liệu
			    document.add(infoTable);
			    
				document.add(new Paragraph("*******************************************************************************", vietnameseFont));
				Paragraph karaokeParagraph = new Paragraph("KARAOKE4T", vietnameseFont);
			    karaokeParagraph.setAlignment(Element.ALIGN_CENTER); // Căn giữa đều
			    document.add(karaokeParagraph);
				document.add(new Paragraph("*******************************************************************************", vietnameseFont));
			
				custom = new CustomDashedLineSeparator();
				custom.setLineWidth(1);
				custom.setOffset(10);
				custom.setLineColor(BaseColor.BLACK);
				document.add(new Chunk(custom));
				
				Paragraph thongtintinhtien = new Paragraph("THÔNG TIN TÍNH TIỀN", vietnameseFont);
				thongtintinhtien.setAlignment(Element.ALIGN_CENTER); // Căn giữa đều
			    document.add(thongtintinhtien);	
			    
				document.add(new Paragraph("\n"));
				custom = new CustomDashedLineSeparator();
				custom.setLineWidth(1);
				custom.setOffset(10);
				custom.setLineColor(BaseColor.BLACK);
				document.add(new Chunk(custom));
				
                document.add(new Paragraph(lbl_tenPhongTT.getText(), vietnameseFont));
                document.add(new Paragraph("\n"));
                
				custom = new CustomDashedLineSeparator();
				custom.setLineWidth(1);
				custom.setOffset(10);
				custom.setLineColor(BaseColor.BLACK);
				document.add(new Chunk(custom));

				PdfPTable infoTable1 = new PdfPTable(2);
			    infoTable1.setWidthPercentage(100);
		
                PdfPCell cellHoTen = new PdfPCell(new Paragraph("Họ tên KH: " + lbl_tenKH.getText(), vietnameseFont));
                cellHoTen.setBorder(Rectangle.NO_BORDER);
                infoTable1.addCell(cellHoTen);
                PdfPCell cellGioNhanPhong = new PdfPCell(new Paragraph("Giờ nhận phòng: " + lbl_gioNhanPhong.getText(), vietnameseFont));
                cellGioNhanPhong.setBorder(Rectangle.NO_BORDER);
                cellGioNhanPhong.setHorizontalAlignment(Element.ALIGN_RIGHT); // Căn phải
                infoTable1.addCell(cellGioNhanPhong);
                infoTable1.completeRow();
                document.add(infoTable1);
              
				PdfPTable infoTable2 = new PdfPTable(2);
			    infoTable2.setWidthPercentage(100);

                PdfPCell cellSDT = new PdfPCell(new Paragraph("SĐT Khách: " + lbl_sdtKH.getText(), vietnameseFont));
                cellSDT.setBorder(Rectangle.NO_BORDER);
                infoTable2.addCell(cellSDT);
                
                PdfPCell cellGioTraPhong = new PdfPCell(new Paragraph("Giờ trả phòng: " + lbl_gioTraPhong.getText(), vietnameseFont));
                cellGioTraPhong.setBorder(Rectangle.NO_BORDER);
                cellGioTraPhong.setHorizontalAlignment(Element.ALIGN_RIGHT);
                infoTable2.addCell(cellGioTraPhong);
                infoTable2.completeRow();
                document.add(infoTable2);
                
				PdfPTable infoTable3 = new PdfPTable(2);
			    infoTable3.setWidthPercentage(100);
                
                PdfPCell celltennv = new PdfPCell(new Paragraph("Họ tên NV: " + lbl_tenNV.getText(), vietnameseFont));
                celltennv.setBorder(Rectangle.NO_BORDER);
                infoTable3.addCell(celltennv);
                
                PdfPCell cellTongThoiLuong = new PdfPCell(new Paragraph("Tổng thời lượng: " + lbl_tongThoiLuong.getText(), vietnameseFont));
                cellTongThoiLuong.setBorder(Rectangle.NO_BORDER);
                cellTongThoiLuong.setHorizontalAlignment(Element.ALIGN_RIGHT);
                infoTable3.addCell(cellTongThoiLuong);
                infoTable3.completeRow();
                document.add(infoTable3);
                document.add(new Paragraph("\n"));
                
                // add table in pdf
                PdfPTable tablePDF = new PdfPTable(model.getColumnCount());
                tablePDF.setWidthPercentage(100);
                // Add table headers
                for (int i = 0; i < model.getColumnCount(); i++) {
                    PdfPCell headerCell = new PdfPCell(new Phrase(model.getColumnName(i), vietnameseFont));
                    headerCell.setBorder(Rectangle.BOTTOM);
                    headerCell.setBorderWidth(0.5f);
                    headerCell.setPaddingBottom(10f); // Adjust the padding as needed
                    headerCell.setBorderColorBottom(BaseColor.BLACK);
                    
                    if (i == 0) {
                    	headerCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Cột "Tên Dịch Vụ"
                    } else if (i == 1) {
                    	headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giá Dịch Vụ"
                    } else {
                    	headerCell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Cột "Số Lượng"
                    }
                    
                    tablePDF.addCell(headerCell);
                }
                // Add table data
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        PdfPCell cell = new PdfPCell(new Phrase(model.getValueAt(row, col).toString(), vietnameseFont));
    	                cell.setBorder(Rectangle.BOTTOM);
    	                cell.setBorderWidth(0.5f);
    	                cell.setPaddingBottom(10f); // Adjust the padding as needed
    	                cell.setBorderColorBottom(BaseColor.BLACK);
    	                
    	                // Căn lề cho ô bảng
    	                if (col == 0) {
    	                    cell.setHorizontalAlignment(Element.ALIGN_LEFT); // Cột "Tên Dịch Vụ"
    	                } else if (col == 1) {
    	                    cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Cột "Giá Dịch Vụ"
    	                } else {
    	                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT); // Cột "Số Lượng"
    	                }
    	                
                        tablePDF.addCell(cell);
                    }
                }
                document.add(tablePDF);
                
                document.add(new Paragraph("\n"));
    			custom = new CustomDashedLineSeparator();
				custom.setLineWidth(1);
				custom.setOffset(10);
				custom.setLineColor(BaseColor.BLACK);
				document.add(new Chunk(custom));
                
				PdfPTable infoTable4 = new PdfPTable(2);
			    infoTable4.setWidthPercentage(100);
			    
			    // format tiền tệ VND
			    double tienTe = Double.parseDouble(txt_tienNhan.getText());
			    String formattedTienNhan = formatCurrency(tienTe);
			    
			    PdfPCell cellTienNhan = new PdfPCell(new Paragraph("Tiền nhận: " + formattedTienNhan, vietnameseFont));
                cellTienNhan.setBorder(Rectangle.NO_BORDER);
                infoTable4.addCell(cellTienNhan);
                
                PdfPCell cellTienDV = new PdfPCell(new Paragraph("Tiền dịch vụ: " + lbl_tienDichVu.getText(), vietnameseFont));
                cellTienDV.setBorder(Rectangle.NO_BORDER);
                cellTienDV.setHorizontalAlignment(Element.ALIGN_RIGHT);
                infoTable4.addCell(cellTienDV);
                infoTable4.completeRow();
                document.add(infoTable4); 

				PdfPTable infoTable5 = new PdfPTable(2);
			    infoTable5.setWidthPercentage(100);
			    
                PdfPCell cellTienThua = new PdfPCell(new Paragraph("Tiền thừa: " + lbl_tienThua.getText(), vietnameseFont));
                cellTienThua.setBorder(Rectangle.NO_BORDER);
                infoTable5.addCell(cellTienThua);
                
                PdfPCell cellTienPhong = new PdfPCell(new Paragraph("Tiền phòng: " + lbl_tienPhong.getText(), vietnameseFont));
                cellTienPhong.setBorder(Rectangle.NO_BORDER);
                cellTienPhong.setHorizontalAlignment(Element.ALIGN_RIGHT);
                infoTable5.addCell(cellTienPhong);         
                infoTable5.completeRow();
                document.add(infoTable5);      
                
				PdfPTable infoTable6 = new PdfPTable(2);
			    infoTable6.setWidthPercentage(100);
                
                PdfPCell cellRong = new PdfPCell(new Paragraph("",  vietnameseFont));
                cellRong.setBorder(Rectangle.NO_BORDER);
                infoTable6.addCell(cellRong);
                
                PdfPCell cellTongTam = new PdfPCell(new Paragraph("Tổng tạm: " + lbl_tongTam.getText(), vietnameseFont));
                cellTongTam.setBorder(Rectangle.NO_BORDER);
                cellTongTam.setHorizontalAlignment(Element.ALIGN_RIGHT);
                infoTable6.addCell(cellTongTam);         
                infoTable6.completeRow();
                document.add(infoTable6); 
                
				PdfPTable infoTable7 = new PdfPTable(2);
				infoTable7.setWidthPercentage(100);
                
                PdfPCell cellRong1 = new PdfPCell(new Paragraph("",  vietnameseFont));
                cellRong1.setBorder(Rectangle.NO_BORDER);
                infoTable7.addCell(cellRong1);
                
                PdfPCell cellKhuyenMai = new PdfPCell(new Paragraph("Khuyến mãi: " + lbGiaTriKhuyenMai.getText(), vietnameseFont));
                cellKhuyenMai.setBorder(Rectangle.NO_BORDER);
                cellKhuyenMai.setHorizontalAlignment(Element.ALIGN_RIGHT);
                infoTable7.addCell(cellKhuyenMai);         
                infoTable7.completeRow();
                document.add(infoTable7); 
                
				PdfPTable infoTable8 = new PdfPTable(2);
				infoTable8.setWidthPercentage(100);
                
                PdfPCell cellRong2 = new PdfPCell(new Paragraph("",  vietnameseFont));
                cellRong2.setBorder(Rectangle.NO_BORDER);
                infoTable8.addCell(cellRong2);
                
                PdfPCell cellVAT = new PdfPCell(new Paragraph("VAT: " + lbl_VAT.getText(), vietnameseFont));
                cellVAT.setBorder(Rectangle.NO_BORDER);
                cellVAT.setHorizontalAlignment(Element.ALIGN_RIGHT);
                infoTable8.addCell(cellVAT);         
                infoTable8.completeRow();
                document.add(infoTable8); 
			    
                document.add(new Paragraph("\n"));
    			custom = new CustomDashedLineSeparator();
				custom.setLineWidth(1);
				custom.setOffset(10);
				custom.setLineColor(BaseColor.BLACK);
				document.add(new Chunk(custom));
                document.add(new Paragraph("\n"));
                String tongcong = ((String) lbl_tongCong.getText()).replace(",", "");
			    double tientongcong = Double.parseDouble(tongcong);
			    String formattedTongCong = formatCurrency(tientongcong);
                Paragraph tongCongParagraph = new Paragraph("TỔNG CỘNG: " + formattedTongCong, vietnameseFont);
                tongCongParagraph.getFont().setStyle(com.itextpdf.text.Font.BOLD);
                tongCongParagraph.setAlignment(Element.ALIGN_RIGHT); // Căn phải
                document.add(tongCongParagraph);
                
                document.add(new Paragraph("\n"));
     			custom = new CustomDashedLineSeparator();
 				custom.setLineWidth(1);
 				custom.setOffset(10);
 				custom.setLineColor(BaseColor.BLACK);
 				document.add(new Chunk(custom));
 				document.add(new Paragraph("\n"));

                Paragraph camOn = new Paragraph("Cảm ơn quý khách đã đến quán", vietnameseFont);
                camOn.getFont().setStyle(com.itextpdf.text.Font.BOLDITALIC);
                camOn.setAlignment(Element.ALIGN_CENTER); // Căn phải
                document.add(camOn);
			}
			// format tiền tệ VND
			private String formatCurrency(double tienTe) {
			    // Create a DecimalFormat with the desired format
			    currencyFormat = new DecimalFormat("###,###,### VND");
			    
			    // Format the amount as currency
			    String formattedAmount = currencyFormat.format(tienTe);
			    
			    return formattedAmount;
			}


			private String getCurrentTime() {
				// TODO Auto-generated method stub
			    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ssa");
			    return timeFormat.format(new Date());
			}
			private String getCurrentDate() {
				// TODO Auto-generated method stub
			    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			    return dateFormat.format(new Date());
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				layTienThua();
				// TODO Auto-generated method stub
				// Check if the customer provided enough money before proceeding with payment
		        String tienNhanStr = txt_tienNhan.getText();
		        
		        String tc = ((String) lbl_tongCong.getText()).replace(",", "");
		        if (tienNhanStr.isEmpty() || Double.parseDouble(tienNhanStr) < Double.parseDouble(tc)) {
		            JOptionPane.showMessageDialog(contentPane, "KHÔNG XUẤT ĐƯỢC HÓA ĐƠN!");
		            return; // Stop further processing if the amount is not enough
		        }
				printToPDF();
		        layMaPhong(maHD);
		        capNhatTrangThaiBanThanhTrong(layMaPhong);
			}
		});
	    contentPane.add(btntstThanhTon);
	    
	    lblNewLabel = new JLabel("VND");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel.setBounds(279, 535, 43, 19);
	    contentPane.add(lblNewLabel);
	    
	    JLabel tenPhong = new JLabel("Tên phòng:");
	    tenPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    tenPhong.setBounds(402, 199, 97, 20);
	    contentPane.add(tenPhong);
	    
	    valueTenPhong = new JLabel("P001");
	    valueTenPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    valueTenPhong.setBounds(496, 199, 49, 20);
	    contentPane.add(valueTenPhong);
	    
	    tinhKhuyenMai(laySDT);
	}
	
	public void loadChiTietDichVuDaDat(String maHD) {
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, username, password);

	        String sql = "select dv.maDV, dv.tenDichVu ,dv.donGia, ctdv.soLuong\r\n"
	        		+ "from ChiTietDichVu ctdv\r\n"
	        		+ "inner join DichVu dv ON dv.maDV = ctdv.maDV\r\n"
	        		+ "inner join ChiTietHoaDon cthd ON cthd.maHD = ctdv.maHD where cthd.maHD = ?";

	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, maHD);

	        ResultSet resultSet = statement.executeQuery();

	        model.setRowCount(0);

	        while (resultSet.next()) {
	            String tenDV = resultSet.getString("tenDichVu");
	            double gia = resultSet.getDouble("donGia");
	            String giaFormatted = currencyFormat.format(gia);
	            int soLuong = resultSet.getInt("soLuong");
	            Object [] ds = {tenDV, giaFormatted, soLuong};
	            model.addRow(ds);
	            
	            
	        }table.setModel(model);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void loadThongTinTinhTien(String maPhong ,String gioKetThuc , String tongThoiLuong , String tongTienDV, String tongTienPhong,Double tongTienHD) {
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        @SuppressWarnings("unused")
		String tam = maPhong;
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "select  tenKH,kh.SDT,thoiGianNhanPhong \r\n"
            		+ "from PhieuDatPhong pdp\r\n"
            		+ "inner join KhachHang kh\r\n"
            		+ "on pdp.maKH = kh.maKH\r\n"
            		+ "inner join NhanVien nv\r\n"
            		+ "on pdp.maNV = nv.maNV\r\n"
            		+ "where maPhong = ? ";
            
            PreparedStatement statement = connection.prepareStatement(sql);
           
            statement.setString(1,maPhong);
           
            ResultSet rs = statement.executeQuery();
            // Lặp qua các dòng kết quả và thêm vào JComboBox
            while (rs.next()) {
               
                String tenKH = rs.getString(1);
                String sdtKH = rs.getString(2);
//                String tenNV = rs.getString(3);
                String tgNhanPhong = rs.getString(3);
                
                tinhKhuyenMai(sdtKH);
                
                lbl_sdtKH.setText(sdtKH);
                lbl_tenKH.setText(tenKH);
//                lbl_tenNV.setText(tenNV);
                lbl_gioNhanPhong.setText(tgNhanPhong);
                lbl_gioTraPhong.setText(gioKetThuc);
                
            }
            
            lbl_tongThoiLuong.setText(tongThoiLuong + " phút");
            if(!tongTienDV.isEmpty()) {
            	// dinh dang tien te
//            	lbl_tienDichVu.setText(tongTienDV.toString() + " VND");
                lbl_tienDichVu.setText(currencyFormat.format(Double.parseDouble(tongTienDV)));
            }else {
            	lbl_tienDichVu.setText("");
            }
            
//            lbl_tienPhong.setText(tongTienPhong.toString() + " VND");
            lbl_tienPhong.setText(currencyFormat.format(Double.parseDouble(tongTienPhong)));
//            lbl_tongTam.setText(tongTienHD.toString());
            lbl_tongTam.setText(currencyFormat.format(tongTienHD));
            Double vat = tongTienHD * VAT;
            // vat 27.466 k 
            Double chietKhau = 0.0;
            if(lbGiaTriKhuyenMai.getText().equals("10%") && lbGiaTriKhuyenMai.getText() != null) {
            	chietKhau = tongTienHD * 0.1;
            	//chiet khau 34.333
            }else {
            	chietKhau = tongTienHD * 0.05;
            }
            Double tongSauVATvaCK = tongTienHD - vat - chietKhau;
            DecimalFormat df = new DecimalFormat("###,###,###");
            lbl_tongCong.setText(df.format(tongSauVATvaCK));
//            String formattedResult = String.format("%,.3f", tongSauVATvaCK).replace(",", ".");
//
//         // Hiển thị kết quả trên lbl_tongCong
//           lbl_tongCong.setText(formattedResult);

            
            
            // Đóng các tài nguyên
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void updateHoaDon(String maHD,String gioKetThuc ,String tongTienHD,String tienKhachTra) {
        // Thông tin kết nối đến cơ sở dữ liệu
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";

        try {
        	Connection connection = DriverManager.getConnection(url, username, password);

            // Thực hiện câu lệnh INSERT
            String sql = "UPDATE [dbo].[HoaDon] SET [gioKetThuc] = ?,[tienKhachTra] = ? ,[tongTien] = ? WHERE [maHD] = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            // Thiết lập giá trị tham số
            statement.setString(1, gioKetThuc);
            statement.setString(2, tienKhachTra);
            statement.setString(3, tongTienHD);
            statement.setString(4, maHD); // Gio Ket Thuc
            
            // Thực hiện câu lệnh INSERT
            statement.executeUpdate();

            // Đóng các tài nguyên
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	// HÀM CHUYỀN TRẠNG THÁI BẬN -> TRỐNG
		public void capNhatTrangThaiBanThanhTrong(String maPhong) {
			// Thông tin kết nối đến cơ sở dữ liệu
			String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
			String username = "sa";
			String password = "123";

			try {
				// Kết nối đến cơ sở dữ liệu
				Connection connection = DriverManager.getConnection(url, username, password);

				// Truy vấn SQL để cập nhật dữ liệu
				String sql = "UPDATE Phong SET maTTP = 'TTP003' WHERE maPhong = ?";
				PreparedStatement statement = connection.prepareStatement(sql);

				// Thiết lập giá trị tham số cho mã phòng
				statement.setString(1, maPhong);

				// Thực hiện câu lệnh UPDATE
				@SuppressWarnings("unused")
				int rowsAffected = statement.executeUpdate();

				// Đóng các tài nguyên
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		

    public void layMaPhong(String maHD) {
    		 // Thông tin kết nối đến cơ sở dữ liệu
    		String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
            String username = "sa";
            String password = "123";

            // Biến chứa kết quả
            String maPhong = null;

            try {
                // Kết nối đến cơ sở dữ liệu
                Connection connection = DriverManager.getConnection(url, username, password);

                // Truy vấn SQL sử dụng PreparedStatement để tránh tình trạng SQL Injection
                String sql = "SELECT maPhong FROM ChiTietHoaDon WHERE maHD = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, maHD); // Thiết lập giá trị cho tham số

                    // Thực hiện truy vấn
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Xử lý kết quả trả về (nếu có)
                        if (resultSet.next()) {
                            maPhong = resultSet.getString("maPhong");
                            layMaPhong = maPhong;
//                            capNhatTrangThaiBanThanhTrong(maPhong);
                            System.out.println("Mã phòng: " + maPhong);
                        } else {
                            System.out.println("Không tìm thấy mã phòng cho mã hóa đơn " + maHD);
                        }
                    }
                }

                // Đóng kết nối
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
    	}
   
    private void kiemTraTienNhan() {
        Border defaultBorder = UIManager.getBorder("TextField.border"); // Lấy border mặc định của text field

        try {
        	float tienNhan = Float.parseFloat(txt_tienNhan.getText());
        	float tongCong = Float.parseFloat(lbl_tongCong.getText());

            if (tienNhan < tongCong) {
                // Nếu tiền nhận bé hơn tổng cộng, đặt viền màu đỏ
                txt_tienNhan.setBorder(BorderFactory.createLineBorder(Color.RED));
            } else {
                // Nếu không, sử dụng viền mặc định
                txt_tienNhan.setBorder(defaultBorder);
            }
        } catch (NumberFormatException ex) {
            // Nếu có lỗi, sử dụng viền màu đỏ
            txt_tienNhan.setBorder(defaultBorder);
        }
    }
    
    void khuyenMai(String km) {
    	khuyenMai = km;
    	System.out.println("Khuyen mai" + km);
    	lbGiaTriKhuyenMai.setText(km);
    }
    
    public String tinhKhuyenMai(String sdtKhachHang) {
        // Thông tin kết nối đến cơ sở dữ liệu
        String url = "jdbc:sqlserver://localhost:1433;databasename=Karaoke4T";
        String username = "sa";
        String password = "123";
        
        // Biến chứa kết quả
        String maKhachHang = null;
        laySDT = sdtKhachHang;
        System.out.println("SDT tim kh"+ laySDT);
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection connection = DriverManager.getConnection(url, username, password);

            // Truy vấn SQL sử dụng PreparedStatement để tránh tình trạng SQL Injection
            String sql = "select maHD,tongTien,tenKH,SDT,kh.maKH from HoaDon hd\r\n"
                    + "inner join KhachHang kh\r\n"
                    + "on hd.maKH = kh.maKH\r\n"
                    + "where SDT = ? AND tongTien > 0";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, laySDT);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả trả về (nếu có)
            while (resultSet.next()) {
                // Có dữ liệu
                String maHD = resultSet.getString("maHD");
                maKhachHang = resultSet.getString("maKH");
                
                if(maKhachHang == null) {
                	khuyenMai("10%");
                }
                else {
                	khuyenMai("5%");
                }
                // Tính khuyến mãi và thực hiện các công việc khác
            }


            // Đóng kết nối
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maKhachHang;
    }

    
    
   public void nhanTenNhanVien(String tenNhanVien) {
	   lbl_tenNV.setText(tenNhanVien);
   }
   public void nhanMaPhong(String maPhong) {
	   layMaPhong = maPhong;
	   valueTenPhong.setText(layMaPhong);
   }
}
