package DAO.Mapper;

import DAO.Mapper.Interfaces.IExcelRowMapper;
import DAO.Mapper.Interfaces.IRowMapper;
import DTO.CT_HoaDonDTO;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;

public class CT_HoaDonMapper implements IRowMapper<CT_HoaDonDTO>, IExcelRowMapper<CT_HoaDonDTO> {
    @Override
    public CT_HoaDonDTO mapRow(ResultSet resultSet) {
        return null;
    }

    @Override
    public void mapExcelHeader(CellStyle cellStyle, Row row) {

    }

    @Override
    public void mapExcelBody(CT_HoaDonDTO dto, Row row) {

    }

    @Override
    public CT_HoaDonDTO mapExcelToDto(CT_HoaDonDTO dto, int columnIndex, String cellValue) {
        return null;
    }
}
