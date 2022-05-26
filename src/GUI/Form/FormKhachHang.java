package GUI.Form;

import BUS.Interfaces.IKhachHangBUS;
import BUS.KhachHangBUS;
import BUS.SearchMapper.KhachHangSearchMapper;
import DTO.Interface.IEntity;
import DTO.KhachHangDTO;
import GUI.Form.Abstract.JTablePanel;
import GUI.FrameSearch;
import GUI.components.TableColumn;
import Utils.General;
import Utils.Validator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormKhachHang extends JTablePanel {
    public FormKhachHang() {
        initComponents();
        fillTable();
    }

    public void fillTable() {
        fillTable(null);
    }

    public void fillTable(ArrayList<IEntity> idList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        IKhachHangBUS khachHangBUS = new KhachHangBUS();

        ArrayList<KhachHangDTO> list = new ArrayList<KhachHangDTO>();
        if (idList == null)
            list = khachHangBUS.findAll();
        else
            for (IEntity entity:idList)
                list.add(khachHangBUS.findByID(entity.getID()));

        for (KhachHangDTO dto: list) {
            Object[] row;
            if (General.CURRENT_ROLE.isAdmin())
                row = new Object[] { "KH" + dto.getMaKH(), dto.getHoTen(), dto.getSDT(), dto.getDiaChi(), dto.getEmail(),
                        dto.getTinhTrang() == 1 ? "Hoạt động" : "Vô hiệu"};
            else row = new Object[] { "KH" + dto.getMaKH(), dto.getHoTen(), dto.getSDT(), dto.getDiaChi(), dto.getEmail() };
            model.addRow(row);
        }
    }
    
    private void initComponents() {
        setLayout(null);

        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setLayout(null);

        lbDetailTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbDetailTitle.setForeground(new Color(37, 57, 111));
        lbDetailTitle.setText("Thông tin khách hàng");
        infoPanel.add(lbDetailTitle);
        lbDetailTitle.setBounds(130, 20, 260, 40);

        lbMaKH.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbMaKH.setText("Mã");
        infoPanel.add(lbMaKH);
        lbMaKH.setBounds(31, 80, 70, 20);

        lbHo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbHo.setText("Họ");
        infoPanel.add(lbHo);
        lbHo.setBounds(231, 80, 70, 20);

        lbTen.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTen.setText("Tên");
        infoPanel.add(lbTen);
        lbTen.setBounds(401, 80, 70, 20);

        lbSDT.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbSDT.setText("Số Đt");
        infoPanel.add(lbSDT);
        lbSDT.setBounds(31, 140, 70, 20);

        lbEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbEmail.setText("Email");
        infoPanel.add(lbEmail);
        lbEmail.setBounds(231, 140, 100, 20);

        txtMaKH.setEnabled(false);
        txtMaKH.setBackground(new Color(245, 245, 245));
        infoPanel.add(txtMaKH);
        txtMaKH.setBounds(30, 100, 170, 35);
        infoPanel.add(txtSDT);
        txtSDT.setBounds(30, 160, 170, 35);
        infoPanel.add(txtHo);
        txtHo.setBounds(230, 100, 160, 35);
        infoPanel.add(txtTen);
        txtTen.setBounds(400, 100, 70, 35);
        infoPanel.add(txtEmail);
        txtEmail.setBounds(230, 160, 240, 35);

        btnThem.setBackground(new Color(220, 247, 227));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnThem.setForeground(new Color(47, 168, 79));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.setFocusPainted(false);
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnThem.getText().equalsIgnoreCase("Thêm"))
                    onClickBtnThemListener();
                else onClickBtnKichHoatListener();
            }
        });
        infoPanel.add(btnThem);
        btnThem.setBounds(30, 320, 170, 35);

        btnSua.setBackground(new Color(252, 243, 215));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSua.setForeground(new Color(243, 170, 24));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.setFocusPainted(false);
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnSuaListener();
            }
        });
        infoPanel.add(btnSua);
        btnSua.setBounds(30, 280, 440, 35);

        btnXoa.setBackground(new Color(254, 228, 226));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setForeground(new Color(234, 61, 47));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.setFocusPainted(false);
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnXoaListener();
            }
        });
        infoPanel.add(btnXoa);
        btnXoa.setBounds(230, 320, 240, 35);

        lbDiaChi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbDiaChi.setText("Địa chỉ");
        infoPanel.add(lbDiaChi);
        lbDiaChi.setBounds(31, 200, 100, 20);
        infoPanel.add(txtDiaChi);
        txtDiaChi.setBounds(30, 220, 440, 35);

        add(infoPanel);
        infoPanel.setBounds(10, 10, 500, 380);

        tablePanel.setBackground(new Color(255, 255, 255));
        tablePanel.setLayout(null);

        lbTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTableTitle.setForeground(new Color(37, 57, 111));
        lbTableTitle.setText("Danh sách");
        tablePanel.add(lbTableTitle);
        lbTableTitle.setBounds(30, 10, 240, 40);

        btnTimKiem.setBackground(new Color(229, 239, 255));
        btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTimKiem.setForeground(new Color(54, 123, 245));
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setBorderPainted(false);
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnTimKiemListener();
            }
        });
        tablePanel.add(btnTimKiem);
        btnTimKiem.setBounds(750, 20, 170, 40);

        btnReset.setIcon(new ImageIcon("bin/images/components/reset.png"));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickBtnResetListener();
            }
        });
        tablePanel.add(btnReset);
        btnReset.setBounds(923, 20, 40, 40);

        jScrollPane = new JScrollPane();
        jScrollPane.setBackground(Color.white);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setFocusable(false);

        table = new TableColumn();
        if (General.CURRENT_ROLE.isAdmin())
            columnHeader = new String [] {
                    "Mã", "Họ tên", "Số điện thoại", "Địa chỉ", "Email", "Tình trạng"
            };
        else columnHeader = new String [] {
                "Mã", "Họ tên", "Số điện thoại", "Địa chỉ", "Email"
        };
        table.setModel(new DefaultTableModel(
                new Object [][] {},
                columnHeader
        ) {
            final boolean[] canEdit = new boolean [columnHeader.length];

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jScrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
        }

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                onClickTableRow();
            }
        });

        tablePanel.add(jScrollPane);
        jScrollPane.setBounds(22, 60, 940, 350);

        add(tablePanel);
        tablePanel.setBounds(10, 400, 980, 410);

        taskPanel.setBackground(new Color(255, 255, 255));
        taskPanel.setLayout(null);

        lbTaskTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbTaskTitle.setForeground(new Color(37, 57, 111));
        lbTaskTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTaskTitle.setText("Mua hàng");
        taskPanel.add(lbTaskTitle);
        lbTaskTitle.setBounds(120, 20, 240, 40);

        lbBought.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbBought.setText("Đã mua");
        taskPanel.add(lbBought);
        lbBought.setBounds(10, 100, 70, 30);

        progressBought.setLayout(null);

        progressBoughtText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressBoughtText.setForeground(new Color(47, 168, 79));
        progressBoughtText.setHorizontalAlignment(SwingConstants.CENTER);
        progressBoughtText.setText("64 sản phẩm (80%)");
        progressBought.add(progressBoughtText);
        progressBoughtText.setBounds(0, 0, 370, 30);

        progressBoughtValue.setBackground(new Color(153, 255, 153));
        progressBoughtValue.setLayout(null);

        progressBought.add(progressBoughtValue);
        progressBoughtValue.setBounds(0, 0, 310, 30);

        taskPanel.add(progressBought);
        progressBought.setBounds(90, 100, 370, 30);

        lbPay.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbPay.setText("Đã chi");
        taskPanel.add(lbPay);
        lbPay.setBounds(10, 160, 70, 30);

        progressPay.setLayout(null);

        progressPayText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressPayText.setForeground(new Color(243, 170, 24));
        progressPayText.setHorizontalAlignment(SwingConstants.CENTER);
        progressPayText.setText("23.000.000 đ (40%)");
        progressPay.add(progressPayText);
        progressPayText.setBounds(0, 0, 370, 30);

        progressPayValue.setBackground(new Color(255, 231, 153));
        progressPayValue.setLayout(null);

        progressPay.add(progressPayValue);
        progressPayValue.setBounds(0, 0, 140, 30);

        taskPanel.add(progressPay);
        progressPay.setBounds(90, 160, 370, 30);

        lbTime.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTime.setForeground(new Color(37, 57, 111));
        lbTime.setText("Thời gian mua:");
        taskPanel.add(lbTime);
        lbTime.setBounds(10, 220, 140, 30);

        lbFavoriteValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteValue.setForeground(new Color(155, 84, 225));
        lbFavoriteValue.setText("Cafe Trung Nguyên");
        taskPanel.add(lbFavoriteValue);
        lbFavoriteValue.setBounds(190, 280, 180, 30);

        lbFavorite.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavorite.setForeground(new Color(37, 57, 111));
        lbFavorite.setText("Sản phẩm yêu thích: ");
        taskPanel.add(lbFavorite);
        lbFavorite.setBounds(10, 280, 180, 30);

        lbTimeValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbTimeValue.setForeground(new Color(155, 84, 225));
        lbTimeValue.setText("10/09/2021 - 23/05/2022");
        taskPanel.add(lbTimeValue);
        lbTimeValue.setBounds(150, 220, 310, 30);

        lbFavoriteCategory.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteCategory.setForeground(new Color(37, 57, 111));
        lbFavoriteCategory.setText("Danh mục yêu thích: ");
        taskPanel.add(lbFavoriteCategory);
        lbFavoriteCategory.setBounds(10, 320, 180, 30);

        lbFavoriteCategoryValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbFavoriteCategoryValue.setForeground(new Color(155, 84, 225));
        lbFavoriteCategoryValue.setText("Cafe đen");
        taskPanel.add(lbFavoriteCategoryValue);
        lbFavoriteCategoryValue.setBounds(190, 320, 180, 30);

        add(taskPanel);
        taskPanel.setBounds(520, 10, 470, 380);
    }

    private KhachHangDTO getUserInput() {
        Integer idNCC = null;
        try {
            idNCC = Integer.valueOf(txtMaKH.getText().replace("KH", ""));
        } catch (NumberFormatException ignored) {}

        KhachHangDTO dto = new KhachHangDTO();
        dto.setMaKH(idNCC);
        dto.setHo(txtHo.getText());
        dto.setTen(txtTen.getText());
        dto.setSDT(txtSDT.getText());
        dto.setDiaChi(txtDiaChi.getText());
        dto.setTinhTrang(1);
        return dto;
    }

    private void onClickBtnThemListener() {
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        try {
            KhachHangDTO dto = getUserInput();
            if (!Validator.isValidName(dto.getHoTen()))
                throw new Exception("Tên không hợp lệ.");
            if (!Validator.isValidPhone(dto.getSDT()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!Validator.isValidEmail(dto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            khachHangBUS.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Thêm khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Thêm khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
        int newIndex = table.getRowCount()-1;
        table.setRowSelectionInterval(newIndex, newIndex);
        JScrollBar bar = jScrollPane.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

    private void onClickBtnKichHoatListener() {
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        try {
            KhachHangDTO newDto = getUserInput();
            KhachHangDTO oldDto = khachHangBUS.findByID(newDto.getMaKH());
            if (oldDto == null)
                throw new Exception("Không tìm thấy khách hàng." );
            oldDto.setTinhTrang(1);
            khachHangBUS.update(oldDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Kích hoạt khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Kích hoạt khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }


    private void onClickBtnSuaListener() {
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        try {
            KhachHangDTO newDto = getUserInput();
            if (newDto.getMaKH() == null)
                throw new Exception("Vui lòng chọn khách hàng.");
            if (khachHangBUS.findByID(newDto.getMaKH()) == null)
                throw new Exception("Không tìm thấy khách hàng." );
            if (!Validator.isValidName(newDto.getHoTen()))
                throw new Exception("Tên không hợp lệ.");
            if (!Validator.isValidPhone(newDto.getSDT()))
                throw new Exception("Số điện thoại không hợp lệ.");
            if (!Validator.isValidEmail(newDto.getEmail()))
                throw new Exception("Email không hợp lệ.");
            khachHangBUS.update(newDto);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Sửa khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Sửa khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        fillTable();
    }

    private void onClickBtnXoaListener() {
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        try {
            KhachHangDTO userInput = getUserInput();
            if (userInput.getMaKH() == null)
                throw new Exception("Vui lòng chọn khách hàng.");
            KhachHangDTO dto = khachHangBUS.findByID(userInput.getMaKH());
            if (dto == null)
                throw new Exception("Không tìm thấy khách hàng." );
            if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0)
                khachHangBUS.delete(dto.getMaKH());
            else {
                dto.setTinhTrang(0);
                khachHangBUS.update(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, "Xóa khách hàng thất bại!\n" + (e.getMessage() == null || e.getMessage().isEmpty() ? "" : e.getMessage()), "Thất bại", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(FormKhachHang.this, "Xóa khách hàng thành công!", "Hoàn tất", JOptionPane.INFORMATION_MESSAGE);
        onClickBtnResetListener();
    }

    private void onClickBtnTimKiemListener() {
        try {
            JFrame frame = new FrameSearch("khách hàng", new KhachHangSearchMapper(), FormKhachHang.class);
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FormKhachHang.this, e.getMessage(), "Không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickBtnResetListener() {
        fillTable();
        for (Component component:infoPanel.getComponents()) {
            if (component instanceof JTextField)
                ((JTextField) component).setText("");
        }
        btnThem.setText("Thêm");
        btnXoa.setText("Xóa");
    }

    private void onClickTableRow() {
        int index = table.getSelectedRow();
        IKhachHangBUS khachHangBUS = new KhachHangBUS();
        int selectedID;
        try {
            selectedID = Integer.parseInt(((String) table.getValueAt(index, 0)).replace("KH", ""));
        } catch (Exception e) {
            return;
        }
        KhachHangDTO dto = khachHangBUS.findByID(selectedID);
        if (dto == null)
            return;

        txtMaKH.setText("KH" + dto.getMaKH());
        txtHo.setText(dto.getHo());
        txtTen.setText(dto.getTen());
        txtSDT.setText(dto.getSDT());
        txtEmail.setText(dto.getEmail());
        txtDiaChi.setText(dto.getDiaChi());

        if (General.CURRENT_ROLE.isAdmin() && dto.getTinhTrang() == 0) {
            btnThem.setText("Kích hoạt");
            btnXoa.setText("Xóa");
            txtDiaChi.setText("Vô hiệu hóa");
        } else {
            btnThem.setText("Thêm");
            btnXoa.setText("Xóa");
        }
    }

    private final JPanel infoPanel = new JPanel();
    private final JLabel lbDetailTitle = new JLabel();
    private final JLabel lbMaKH = new JLabel();
    private final JLabel lbHo = new JLabel();
    private final JLabel lbTen = new JLabel();
    private final JLabel lbSDT = new JLabel();
    private final JLabel lbEmail = new JLabel();
    private final JTextField txtMaKH = new JTextField();
    private final JTextField txtSDT = new JTextField();
    private final JTextField txtHo = new JTextField();
    private final JTextField txtTen = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final JButton btnThem = new JButton();
    private final JButton btnSua = new JButton();
    private final JButton btnXoa = new JButton();
    private final JLabel lbDiaChi = new JLabel();
    private final JTextField txtDiaChi = new JTextField();
    private final JPanel tablePanel = new JPanel();
    private final JLabel lbTableTitle = new JLabel();
    private final JButton btnTimKiem = new JButton();
    private final JButton btnReset = new JButton();
    private final JPanel taskPanel = new JPanel();
    private final JLabel lbTaskTitle = new JLabel();
    private final JLabel lbBought = new JLabel();
    private final JPanel progressBought = new JPanel();
    private final JLabel progressBoughtText = new JLabel();
    private final JPanel progressBoughtValue = new JPanel();
    private final JLabel lbPay = new JLabel();
    private final JPanel progressPay = new JPanel();
    private final JLabel progressPayText = new JLabel();
    private final JPanel progressPayValue = new JPanel();
    private final JLabel lbTime = new JLabel();
    private final JLabel lbFavoriteValue = new JLabel();
    private final JLabel lbFavorite = new JLabel();
    private final JLabel lbTimeValue = new JLabel();
    private final JLabel lbFavoriteCategory = new JLabel();
    private final JLabel lbFavoriteCategoryValue = new JLabel();
}
