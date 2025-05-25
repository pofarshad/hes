package com.vpnreseller.core_data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.vpnreseller.core_data.local.entity.InvoiceHeaderEntity;
import com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class InvoiceDao_Impl implements InvoiceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<InvoiceHeaderEntity> __insertionAdapterOfInvoiceHeaderEntity;

  private final EntityInsertionAdapter<InvoiceLineItemEntity> __insertionAdapterOfInvoiceLineItemEntity;

  private final EntityDeletionOrUpdateAdapter<InvoiceHeaderEntity> __deletionAdapterOfInvoiceHeaderEntity;

  private final EntityDeletionOrUpdateAdapter<InvoiceLineItemEntity> __deletionAdapterOfInvoiceLineItemEntity;

  private final EntityDeletionOrUpdateAdapter<InvoiceHeaderEntity> __updateAdapterOfInvoiceHeaderEntity;

  private final EntityDeletionOrUpdateAdapter<InvoiceLineItemEntity> __updateAdapterOfInvoiceLineItemEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLineItemsByInvoiceId;

  public InvoiceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInvoiceHeaderEntity = new EntityInsertionAdapter<InvoiceHeaderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `invoice_headers` (`id`,`representativeId`,`generationDate`,`totalAmount`,`status`,`isSent`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceHeaderEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRepresentativeId());
        statement.bindLong(3, entity.getGenerationDate());
        statement.bindDouble(4, entity.getTotalAmount());
        statement.bindString(5, entity.getStatus());
        final int _tmp = entity.isSent() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
    this.__insertionAdapterOfInvoiceLineItemEntity = new EntityInsertionAdapter<InvoiceLineItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `invoice_line_items` (`id`,`invoiceHeaderId`,`description`,`quantity`,`unitPrice`,`totalPrice`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceLineItemEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getInvoiceHeaderId());
        statement.bindString(3, entity.getDescription());
        statement.bindLong(4, entity.getQuantity());
        statement.bindDouble(5, entity.getUnitPrice());
        statement.bindDouble(6, entity.getTotalPrice());
      }
    };
    this.__deletionAdapterOfInvoiceHeaderEntity = new EntityDeletionOrUpdateAdapter<InvoiceHeaderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `invoice_headers` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceHeaderEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__deletionAdapterOfInvoiceLineItemEntity = new EntityDeletionOrUpdateAdapter<InvoiceLineItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `invoice_line_items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceLineItemEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfInvoiceHeaderEntity = new EntityDeletionOrUpdateAdapter<InvoiceHeaderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `invoice_headers` SET `id` = ?,`representativeId` = ?,`generationDate` = ?,`totalAmount` = ?,`status` = ?,`isSent` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceHeaderEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRepresentativeId());
        statement.bindLong(3, entity.getGenerationDate());
        statement.bindDouble(4, entity.getTotalAmount());
        statement.bindString(5, entity.getStatus());
        final int _tmp = entity.isSent() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindString(7, entity.getId());
      }
    };
    this.__updateAdapterOfInvoiceLineItemEntity = new EntityDeletionOrUpdateAdapter<InvoiceLineItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `invoice_line_items` SET `id` = ?,`invoiceHeaderId` = ?,`description` = ?,`quantity` = ?,`unitPrice` = ?,`totalPrice` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceLineItemEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getInvoiceHeaderId());
        statement.bindString(3, entity.getDescription());
        statement.bindLong(4, entity.getQuantity());
        statement.bindDouble(5, entity.getUnitPrice());
        statement.bindDouble(6, entity.getTotalPrice());
        statement.bindString(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteLineItemsByInvoiceId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM invoice_line_items WHERE invoiceHeaderId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertInvoiceHeader(final InvoiceHeaderEntity invoiceHeader,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfInvoiceHeaderEntity.insert(invoiceHeader);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLineItem(final InvoiceLineItemEntity lineItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfInvoiceLineItemEntity.insert(lineItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLineItems(final List<InvoiceLineItemEntity> lineItems,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfInvoiceLineItemEntity.insert(lineItems);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteInvoiceHeader(final InvoiceHeaderEntity invoiceHeader,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfInvoiceHeaderEntity.handle(invoiceHeader);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLineItem(final InvoiceLineItemEntity lineItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfInvoiceLineItemEntity.handle(lineItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateInvoiceHeader(final InvoiceHeaderEntity invoiceHeader,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvoiceHeaderEntity.handle(invoiceHeader);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLineItem(final InvoiceLineItemEntity lineItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvoiceLineItemEntity.handle(lineItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLineItemsByInvoiceId(final String invoiceHeaderId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLineItemsByInvoiceId.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, invoiceHeaderId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteLineItemsByInvoiceId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<InvoiceHeaderEntity>> getAllInvoiceHeaders() {
    final String _sql = "SELECT * FROM invoice_headers ORDER BY generationDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"invoice_headers"}, new Callable<List<InvoiceHeaderEntity>>() {
      @Override
      @NonNull
      public List<InvoiceHeaderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfGenerationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "generationDate");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIsSent = CursorUtil.getColumnIndexOrThrow(_cursor, "isSent");
          final List<InvoiceHeaderEntity> _result = new ArrayList<InvoiceHeaderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvoiceHeaderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final long _tmpGenerationDate;
            _tmpGenerationDate = _cursor.getLong(_cursorIndexOfGenerationDate);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final boolean _tmpIsSent;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSent);
            _tmpIsSent = _tmp != 0;
            _item = new InvoiceHeaderEntity(_tmpId,_tmpRepresentativeId,_tmpGenerationDate,_tmpTotalAmount,_tmpStatus,_tmpIsSent);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<InvoiceHeaderEntity>> getInvoiceHeadersByRepresentative(
      final String representativeId) {
    final String _sql = "SELECT * FROM invoice_headers WHERE representativeId = ? ORDER BY generationDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, representativeId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"invoice_headers"}, new Callable<List<InvoiceHeaderEntity>>() {
      @Override
      @NonNull
      public List<InvoiceHeaderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfGenerationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "generationDate");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIsSent = CursorUtil.getColumnIndexOrThrow(_cursor, "isSent");
          final List<InvoiceHeaderEntity> _result = new ArrayList<InvoiceHeaderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvoiceHeaderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final long _tmpGenerationDate;
            _tmpGenerationDate = _cursor.getLong(_cursorIndexOfGenerationDate);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final boolean _tmpIsSent;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSent);
            _tmpIsSent = _tmp != 0;
            _item = new InvoiceHeaderEntity(_tmpId,_tmpRepresentativeId,_tmpGenerationDate,_tmpTotalAmount,_tmpStatus,_tmpIsSent);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getInvoiceHeaderById(final String id,
      final Continuation<? super InvoiceHeaderEntity> $completion) {
    final String _sql = "SELECT * FROM invoice_headers WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<InvoiceHeaderEntity>() {
      @Override
      @Nullable
      public InvoiceHeaderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfGenerationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "generationDate");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIsSent = CursorUtil.getColumnIndexOrThrow(_cursor, "isSent");
          final InvoiceHeaderEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final long _tmpGenerationDate;
            _tmpGenerationDate = _cursor.getLong(_cursorIndexOfGenerationDate);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final boolean _tmpIsSent;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSent);
            _tmpIsSent = _tmp != 0;
            _result = new InvoiceHeaderEntity(_tmpId,_tmpRepresentativeId,_tmpGenerationDate,_tmpTotalAmount,_tmpStatus,_tmpIsSent);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<InvoiceHeaderEntity>> getUnpaidInvoices() {
    final String _sql = "SELECT * FROM invoice_headers WHERE status = 'UNPAID' ORDER BY generationDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"invoice_headers"}, new Callable<List<InvoiceHeaderEntity>>() {
      @Override
      @NonNull
      public List<InvoiceHeaderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfGenerationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "generationDate");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIsSent = CursorUtil.getColumnIndexOrThrow(_cursor, "isSent");
          final List<InvoiceHeaderEntity> _result = new ArrayList<InvoiceHeaderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvoiceHeaderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final long _tmpGenerationDate;
            _tmpGenerationDate = _cursor.getLong(_cursorIndexOfGenerationDate);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final boolean _tmpIsSent;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSent);
            _tmpIsSent = _tmp != 0;
            _item = new InvoiceHeaderEntity(_tmpId,_tmpRepresentativeId,_tmpGenerationDate,_tmpTotalAmount,_tmpStatus,_tmpIsSent);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<InvoiceHeaderEntity>> getInvoicesByStatus(final String status) {
    final String _sql = "SELECT * FROM invoice_headers WHERE status = ? ORDER BY generationDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, status);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"invoice_headers"}, new Callable<List<InvoiceHeaderEntity>>() {
      @Override
      @NonNull
      public List<InvoiceHeaderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfGenerationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "generationDate");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIsSent = CursorUtil.getColumnIndexOrThrow(_cursor, "isSent");
          final List<InvoiceHeaderEntity> _result = new ArrayList<InvoiceHeaderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvoiceHeaderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final long _tmpGenerationDate;
            _tmpGenerationDate = _cursor.getLong(_cursorIndexOfGenerationDate);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            final boolean _tmpIsSent;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSent);
            _tmpIsSent = _tmp != 0;
            _item = new InvoiceHeaderEntity(_tmpId,_tmpRepresentativeId,_tmpGenerationDate,_tmpTotalAmount,_tmpStatus,_tmpIsSent);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<InvoiceLineItemEntity>> getLineItemsByInvoiceId(final String invoiceHeaderId) {
    final String _sql = "SELECT * FROM invoice_line_items WHERE invoiceHeaderId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, invoiceHeaderId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"invoice_line_items"}, new Callable<List<InvoiceLineItemEntity>>() {
      @Override
      @NonNull
      public List<InvoiceLineItemEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvoiceHeaderId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceHeaderId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final List<InvoiceLineItemEntity> _result = new ArrayList<InvoiceLineItemEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvoiceLineItemEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpInvoiceHeaderId;
            _tmpInvoiceHeaderId = _cursor.getString(_cursorIndexOfInvoiceHeaderId);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            _item = new InvoiceLineItemEntity(_tmpId,_tmpInvoiceHeaderId,_tmpDescription,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getLineItemById(final String id,
      final Continuation<? super InvoiceLineItemEntity> $completion) {
    final String _sql = "SELECT * FROM invoice_line_items WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<InvoiceLineItemEntity>() {
      @Override
      @Nullable
      public InvoiceLineItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvoiceHeaderId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceHeaderId");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final InvoiceLineItemEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpInvoiceHeaderId;
            _tmpInvoiceHeaderId = _cursor.getString(_cursorIndexOfInvoiceHeaderId);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            _result = new InvoiceLineItemEntity(_tmpId,_tmpInvoiceHeaderId,_tmpDescription,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalAmountByRepresentative(final String representativeId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(totalAmount) FROM invoice_headers WHERE representativeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, representativeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUnpaidAmountByRepresentative(final String representativeId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(totalAmount) FROM invoice_headers WHERE representativeId = ? AND status = 'UNPAID'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, representativeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getInvoiceCountByRepresentative(final String representativeId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM invoice_headers WHERE representativeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, representativeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
