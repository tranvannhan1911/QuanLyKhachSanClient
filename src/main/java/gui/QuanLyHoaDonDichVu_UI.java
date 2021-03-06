package gui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
//import java.awt.*;
//import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import app.Client;
import dao.PhongDao;
import model.ChiTietDV;
import model.ChiTietHoaDonPhong;
import model.DichVu;
import model.HoaDonDV;
import model.HoaDonPhong;
import model.KhachHang;
import model.LoaiPhong;
import model.Phong;
import utils.Currency;
import utils.Ngay;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
//import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


public class QuanLyHoaDonDichVu_UI extends JFrame{
	private Client client;
    private int maHD = 0;
    
    public JPanel pnMain;
    public String maPhong = "0";
    private ImageIcon icon_add = new ImageIcon("data/images/add.png");
    private ImageIcon icon_refresh = new ImageIcon("data/images/refresh.png");
    private ImageIcon icon_trash = new ImageIcon("data/images/trash.png");
    private ImageIcon icon_edit = new ImageIcon("data/images/edit.png");
    private ImageIcon icon_search = new ImageIcon("data/images/magnifying-glass.png");
    private JTextField textField;
    private JTextField txtNgayHetHan;
    private JTextField textField_1;
    private JTextField txtTimKiem;
	private DefaultTableModel modelChiTietDon;
	private JTable tblChiTietDon;
	private JButton btnLamMoi;
	private kDatePicker dpNgayLap;
	private JPanel contentPane;
	private JTextField txtMaHD;
	private JComboBox cboTinhTrang;
	private List<HoaDonDV> dshddv;
	private Date tuNgay;
	private Date denNgay;
	private String error;
	private JComboBox cboTimKiem;
	private JButton btnTimKiem;
	private String where_sql = "";
	private JTable tblDSHDDV;
	private DefaultTableModel modelDSHDDV;
	private List<ChiTietDV> dscthddv = new ArrayList<ChiTietDV>();
	private JButton btnSuaChiTietHD;
     
    
    
    public static void main(String[] args) throws IOException, NotBoundException {
		QuanLyHoaDonDichVu_UI datPhongUI = new QuanLyHoaDonDichVu_UI();
//		datPhongUI.start();
		datPhongUI.setVisible(true);
		datPhongUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
    public QuanLyHoaDonDichVu_UI() throws IOException, NotBoundException{
    	client = new Client();
    	start();
    }

    public void start() throws MalformedURLException, RemoteException, NotBoundException{

        renderGUI();
        renderData();
    }

    public void renderGUI() {
    	setSize(1350, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_11 = new JPanel();
        contentPane.add(panel_11, BorderLayout.NORTH);
        
        JLabel lblQuanLyHoaDonDV = new JLabel("Qu???n l?? h??a ????n d???ch v???");
        lblQuanLyHoaDonDV.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_11.add(lblQuanLyHoaDonDV);
        
        JPanel panel_10 = new JPanel();
        contentPane.add(panel_10, BorderLayout.CENTER);
        panel_10.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel_10.add(panel, BorderLayout.WEST);
        
        JPanel panel_2 = new JPanel();
        panel.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
        
        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4);
        
        JLabel lblThongTinDatPhong = new JLabel("Th??ng tin h??a ????n");
        lblThongTinDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_4.add(lblThongTinDatPhong);
        
        JPanel panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3);
        
        JLabel lblMaHD = new JLabel("M?? h??a ????n");
        lblMaHD.setPreferredSize(new Dimension(150, 30));
        panel_3.add(lblMaHD);
        
        txtMaHD = new JTextField();
        txtMaHD.setEditable(false);
        panel_3.add(txtMaHD);
        txtMaHD.setColumns(20);
        
        
        JPanel panel_3_1_2_1 = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) panel_3_1_2_1.getLayout();
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_2_1);
        
        JLabel lblNgayLap = new JLabel("Ng??y l???p");
        lblNgayLap.setPreferredSize(new Dimension(150, 30));
        panel_3_1_2_1.add(lblNgayLap);
        
        dpNgayLap = new kDatePicker(222);
        dpNgayLap.setPreferredSize(new Dimension(222, 20));
        dpNgayLap.btn.setEnabled(false);
        panel_3_1_2_1.add(dpNgayLap);
        
        JPanel panel_3_1_2_1_1_1 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_3_1_2_1_1_1.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_3_1_2_1_1_1);
        
        JLabel lblTnhTrng = new JLabel("T??nh tr???ng");
        lblTnhTrng.setPreferredSize(new Dimension(150, 30));
        panel_3_1_2_1_1_1.add(lblTnhTrng);
        
        
        cboTinhTrang = new JComboBox();
        cboTinhTrang.setPreferredSize(new Dimension(222, 22));
        panel_3_1_2_1_1_1.add(cboTinhTrang);
        cboTinhTrang.addItem("Ch??a thanh to??n");
        cboTinhTrang.addItem("???? thanh to??n");
        
        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5);
        panel_5.setLayout(new GridLayout(0, 2, 10, 5));
        
        JButton btnSua = new JButton("S???a");
        btnSua.setBackground(Color.WHITE);
        panel_5.add(btnSua);
        
        JButton btnXoa = new JButton("X??a");
        btnXoa.setBackground(Color.WHITE);
        panel_5.add(btnXoa);
        
        btnSuaChiTietHD = new JButton("S???a chi ti???t h??a ????n");
        btnSuaChiTietHD.setBackground(Color.WHITE);
        panel_5.add(btnSuaChiTietHD);
        btnSuaChiTietHD.setEnabled(false);
        
        btnLamMoi = new JButton("L??m m???i");
        btnLamMoi.setBackground(Color.WHITE);
        panel_5.add(btnLamMoi);
        
        JPanel panel_1 = new JPanel();
        panel_10.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6, BorderLayout.NORTH);
        
        JLabel lblTimKiemTheo = new JLabel("T??m ki???m theo: ");
        panel_6.add(lblTimKiemTheo);
        
        cboTimKiem = new JComboBox();
        cboTimKiem.addItem("M?? h??a ????n");
        cboTimKiem.addItem("T??n Kh??ch h??ng");
        cboTimKiem.addItem("CMND");
        cboTimKiem.addItem("S??? ??i???n tho???i");
        panel_6.add(cboTimKiem);
        
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(150, 26));
        panel_6.add(txtTimKiem);
        txtTimKiem.setColumns(10);
        
        btnTimKiem = new JButton("T??m ki???m");
        btnTimKiem.setBackground(Color.WHITE);
        panel_6.add(btnTimKiem);
        
        JButton btnDatLai = new JButton("L??m m???i d??? li???u");
        btnDatLai.setBackground(Color.WHITE);
        panel_6.add(btnDatLai);
        
        
        
        
        
        JPanel panel_8 = new JPanel();
        panel_1.add(panel_8, BorderLayout.CENTER);
        
        String[] cols = {"M?? h??a ????n", "T??n kh??ch h??ng", "CMND", "S??? ??i???n tho???i", "Lo???i kh??ch h??ng", "Ng??y l???p", "T??nh tr???ng", "T???ng ti???n"};
        modelDSHDDV = new DefaultTableModel(cols, 0);
        panel_8.setLayout(new GridLayout(2, 1, 0, 0));
        tblDSHDDV = new JTable(modelDSHDDV);
        JScrollPane scrollPane = new JScrollPane(tblDSHDDV);
        panel_8.add(scrollPane);
        
        JPanel panel_9 = new JPanel();
        panel_9.setBorder(new TitledBorder(null, "Danh s??ch d???ch v???", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_8.add(panel_9);
        panel_9.setLayout(new BorderLayout(0, 0));
        
        String[] cols2 = {"M?? d???ch v???", "T??n d???ch v???", "????n gi??", "S??? l?????ng", "Th??nh ti???n"};
        modelChiTietDon = new DefaultTableModel(cols2, 0);
        
        JPanel panel_7 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
        flowLayout_2.setAlignment(FlowLayout.RIGHT);
        panel_9.add(panel_7, BorderLayout.NORTH);
        
        JButton btnLamMoiDSDV = new JButton("L??m m???i d??? li???u");
        btnLamMoiDSDV.setBackground(Color.WHITE);
        panel_7.add(btnLamMoiDSDV);
        tblChiTietDon = new JTable(modelChiTietDon);
        JScrollPane scrollPane_1 = new JScrollPane(tblChiTietDon);
        panel_9.add(scrollPane_1);
        
        btnLamMoi.addActionListener((e) -> {
        	
        	tblDSHDDV.clearSelection();
        	clear();
        	clearCTHDDV();
        });
        
        btnLamMoiDSDV.addActionListener((e) -> {
        	int idx = tblDSHDDV.getSelectedRow();
        	if(idx != -1) {
        		HoaDonDV hddv = dshddv.get(idx);
        		try {
					renderDSDV(hddv);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        });
        
        btnSua.addActionListener((e) -> {
        	int idx = tblDSHDDV.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui l??ng ch???n h??a ????n ????? s???a");
        		return;
        	}
        	HoaDonDV hddv = dshddv.get(idx);
//        	check t??nh tr???ng
        	int tinhTrang = cboTinhTrang.getSelectedIndex(); 
        	
        	if(tinhTrang < hddv.getTinhTrang()) {
        		JOptionPane.showMessageDialog(contentPane, "T??nh tr???ng kh??ng h???p l???");
        		return;
        	}
        	
        	hddv.setTinhTrang(tinhTrang);
        	try {
				if(client.getHoaDonDVDao().capNhatTinhTrang(hddv)) {
					JOptionPane.showMessageDialog(contentPane, "S???a th??nh c??ng");
					clear();
					renderDSHD();
					return;
				}
				
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	JOptionPane.showMessageDialog(contentPane, "S???a th???t b???i");
        });
//        
        btnXoa.addActionListener((e) -> {
        	int idx = tblDSHDDV.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Vui l??ng ch???n h??a ????n ????? x??a");
        		return ;
        	}
        	
        	int choose = JOptionPane.showConfirmDialog(contentPane, "Ch???c ch???n x??a ?");
        	
        	if(choose != 0)
        		return;
        	
        	try {
				if(client.getHoaDonDVDao().xoaHoaDonDV(dshddv.get(idx).getMaHDDV())) {
					JOptionPane.showMessageDialog(contentPane, "X??a th??nh c??ng");
					renderData();
					clearCTHDDV();
	        		return ;
				}
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				e1.printStackTrace();
			}
        	
        	JOptionPane.showMessageDialog(contentPane, "X??a th???t b???i");
        });
//        
//        btnXemLichDat.addActionListener((e) -> {
//        	int idx2 = tblChiTietDon.getSelectedRow();
//        	if(idx2 == -1) {
//        		JOptionPane.showMessageDialog(contentPane, "Vui l??ng ch???n ph??ng ????? xem l???ch ?????t");
//        		return ;
//        	}
//        	String maPhong = "";
//        	maPhong = dscthdp.get(idx2).getPhong().getMaPhong();
//        	
//        	DialogLichDatPhong dialog = new DialogLichDatPhong();
//        	dialog.setMaPhong(maPhong);
//        	dialog.renderData();
//        	dialog.setVisible(true);
//        });
//        
        btnTimKiem.addActionListener((e) -> {
        	String value = txtTimKiem.getText();
        	where_sql = "";
        	if(cboTimKiem.getSelectedIndex() == 0) {
        		where_sql = " HoaDonDV.maHDDV like N'%"+value+"%' ";
        	}else if(cboTimKiem.getSelectedIndex() == 1){
        		where_sql = " KhachHang.TenKH like N'%"+value+"%' ";
        	}else if(cboTimKiem.getSelectedIndex() == 2){
        		where_sql = " KhachHang.CMND like N'%"+value+"%' ";
        	}else if(cboTimKiem.getSelectedIndex() == 3){
        		where_sql = " KhachHang.soDienThoai like N'%"+value+"%' ";
        	}
        	
        	try {
				renderDSHD();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				e1.printStackTrace();
			}
        });
        
        btnDatLai.addActionListener((e) -> {
        	
        	where_sql = "";
        	txtTimKiem.setText("");
        	clearCTHDDV();
        	try {
				renderDSHD();
			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				e1.printStackTrace();
			}
        });
        
        tblDSHDDV.getSelectionModel().addListSelectionListener((e) -> {
        	int idx = tblDSHDDV.getSelectedRow();
        	if(idx != -1) {
        		HoaDonDV hddv = dshddv.get(idx);
        		txtMaHD.setText(String.valueOf(hddv.getMaHDDV()));
        		dpNgayLap.setValue(hddv.getNgayGioDat());
        		cboTinhTrang.setSelectedIndex(hddv.getTinhTrang());
        		
        		btnSuaChiTietHD.setEnabled(true);
        		try {
    				renderDSDV(hddv);
    			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
    				e1.printStackTrace();
    			}
        	}
        	
        });
        
        
        btnSuaChiTietHD.addActionListener((e) -> {
        	int idx = tblDSHDDV.getSelectedRow();
        	if(idx == -1) {
        		JOptionPane.showMessageDialog(contentPane, "Ch???n h??a ????n ????? s???a");
        		return;
        	}
        	
        	HoaDonDV hddv = dshddv.get(idx);
        	hddv.setDsChiTietDV(dscthddv);
        	SuDungDV_UI pgSuDungDv = new SuDungDV_UI();
        	
        	pgSuDungDv.set_hddv(hddv);
        	pgSuDungDv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	pgSuDungDv.setVisible(true);
        	pgSuDungDv.addWindowListener(new WindowAdapter() {
        		@Override
        	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        	        try {
        	        	
						renderData();
						tblDSHDDV.setRowSelectionInterval(idx, idx);
						renderDSDV(dshddv.get(idx));
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	        pgSuDungDv.dispose();
        	    }
        	});
        	
        	
        });
        
    }

    public void renderData() throws MalformedURLException, RemoteException, NotBoundException{
    	renderDSHD();
    }
    
    public void renderDSHD() throws MalformedURLException, RemoteException, NotBoundException{
    	clear();
    	if(where_sql.equals("")) {
    		dshddv = client.getHoaDonDVDao().getListHDDV();
    	}else {
    		dshddv = client.getHoaDonDVDao().timKiemHDDV(where_sql);
    		
    		if(dshddv.size() == 0) {
    			JOptionPane.showMessageDialog(contentPane, "Kh??ng t??m th???y");
    		}
    	}
    	
    	
    	tblDSHDDV.clearSelection();
    	modelDSHDDV.getDataVector().removeAllElements();
    	dshddv.forEach(hddv -> {
//    		 {"M?? h??a ????n", "T??n kh??ch h??ng", "CMND", "S??? ??i???n tho???i", "Lo???i kh??ch h??ng", "Ng??y l???p", "T??nh tr???ng", "T???ng ti???n"};
    		try {
				modelDSHDDV.addRow(new Object[] {
						hddv.getMaHDDV(),
						hddv.getKhachHang().getTenKH(),
						hddv.getKhachHang().getCmnd(),
						hddv.getKhachHang().getSoDienThoai(),
						hddv.getKhachHang().getLoaiKH(),
						hddv.getNgayGioDat(),
						convertTinhTrang(hddv.getTinhTrang()),
						Currency.format(tinhTongTien(hddv))
				});
			} catch (RemoteException | MalformedURLException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    	tblDSHDDV.revalidate();
    	tblDSHDDV.repaint();
    }
    
    public double tinhTongTien(HoaDonDV hddv) throws RemoteException, MalformedURLException, NotBoundException {
    	List<ChiTietDV> dscthddv2 = client.getChiTietDVDao().getListChiTietDVByMaHDDV(hddv.getMaHDDV());
    	double tongTien = 0.0;
    	for(int i=0; i<dscthddv2.size(); i++) {
    		tongTien += dscthddv2.get(i).getSoLuong() *dscthddv2.get(i).getDichVu().getDonGia();
    	}
    	return tongTien;
    }

    public void renderDSDV(HoaDonDV hddv) throws MalformedURLException, RemoteException, NotBoundException{
        dscthddv = client.getChiTietDVDao().getListChiTietDVByMaHDDV(hddv.getMaHDDV());
        
        tblChiTietDon.clearSelection();
        modelChiTietDon.getDataVector().removeAllElements();
        dscthddv.forEach(cthddv -> {
        	DichVu dv = cthddv.getDichVu();
        	modelChiTietDon.addRow(new Object[] {
        		dv.getMaDV(),
        		dv.getTenDV(),
        		Currency.format(dv.getDonGia()),
        		cthddv.getSoLuong(),
        		Currency.format(cthddv.getSoLuong()*dv.getDonGia())
        	});
        });
        tblChiTietDon.revalidate();
        tblChiTietDon.repaint();
        
    }


    
    
    public void clear() {
    	btnSuaChiTietHD.setEnabled(false);
    	tblDSHDDV.clearSelection();
    	txtMaHD.setText("");
    	cboTinhTrang.setSelectedIndex(0);
    	dpNgayLap.setValueToDay();
    }
    
    public void clearCTHDDV() {
    	dscthddv.clear();
    	modelChiTietDon.getDataVector().removeAllElements();
    	tblChiTietDon.revalidate();
    	tblChiTietDon.repaint();
    }
    
    public boolean checkDate() {
    	
//    	Date homNay = Ngay.homNay(); 
//    	Date tuNgay = Ngay.homNay(), denNgay = Ngay.homNay();
//      
//		try {
//			tuNgay = dpNgayLap.getFullDate();
//			denNgay = dpDenNgay.getFullDate();
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		if (!tuNgay.toString().equals(homNay.toString()) && tuNgay.before(homNay)) {
//			JOptionPane.showMessageDialog(contentPane, "Ng??y ?????n ph???i kh??ng ???????c tr?????c ng??y hi???n t???i");
//			return false;
//		}
//
//		if (!tuNgay.toString().equals(denNgay.toString()) && denNgay.before(tuNgay)) {
//			JOptionPane.showMessageDialog(contentPane, "Ng??y ??i kh??ng ???????c tr?????c ng??y ?????n");
//			return false;
//		}

		return true;
    }

    
    private String convertTinhTrang(int tinhTrang) {
        String result = "";
        if (tinhTrang == 0)
            result = "Ch??a thanh to??n";
        else if (tinhTrang == 1)
            result = "???? thanh to??n";
        return result;
    }


    public void renderError(JTextField a, String message){
        a.requestFocus();
        a.selectAll();
        JOptionPane.showMessageDialog(pnMain, message);
    }

    
    public JPanel getContentPane() {
    	return contentPane;
    }
}
