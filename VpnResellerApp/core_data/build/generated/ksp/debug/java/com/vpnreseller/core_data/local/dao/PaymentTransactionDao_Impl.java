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
import com.vpnreseller.core_data.local.entity.PaymentTransactionEntity;
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
public final class PaymentTransactionDao_Impl implements PaymentTransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PaymentTransactionEntity> __insertionAdapterOfPaymentTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<PaymentTransactionEntity> __deletionAdapterOfPaymentTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<PaymentTransactionEntity> __updateAdapterOfPaymentTransactionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePaymentTransactionById;

  public PaymentTransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPaymentTransactionEntity = new EntityInsertionAdapter<PaymentTransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `payment_transactions` (`id`,`representativeId`,`invoiceId`,`paymentDate`,`amountPaid`,`paymentMethod`,`notes`,`type`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentTransactionEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRepresentativeId());
        if (entity.getInvoiceId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getInvoiceId());
        }
        statement.bindLong(4, entity.getPaymentDate());
        statement.bindDouble(5, entity.getAmountPaid());
        statement.bindString(6, entity.getPaymentMethod());
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindString(8, entity.getType());
      }
    };
    this.__deletionAdapterOfPaymentTransactionEntity = new EntityDeletionOrUpdateAdapter<PaymentTransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `payment_transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentTransactionEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfPaymentTransactionEntity = new EntityDeletionOrUpdateAdapter<PaymentTransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `payment_transactions` SET `id` = ?,`representativeId` = ?,`invoiceId` = ?,`paymentDate` = ?,`amountPaid` = ?,`paymentMethod` = ?,`notes` = ?,`type` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PaymentTransactionEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRepresentativeId());
        if (entity.getInvoiceId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getInvoiceId());
        }
        statement.bindLong(4, entity.getPaymentDate());
        statement.bindDouble(5, entity.getAmountPaid());
        statement.bindString(6, entity.getPaymentMethod());
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindString(8, entity.getType());
        statement.bindString(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeletePaymentTransactionById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM payment_transactions WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertPaymentTransaction(final PaymentTransactionEntity paymentTransaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPaymentTransactionEntity.insert(paymentTransaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPaymentTransactions(final List<PaymentTransactionEntity> paymentTransactions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPaymentTransactionEntity.insert(paymentTransactions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePaymentTransaction(final PaymentTransactionEntity paymentTransaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPaymentTransactionEntity.handle(paymentTransaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePaymentTransaction(final PaymentTransactionEntity paymentTransaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPaymentTransactionEntity.handle(paymentTransaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePaymentTransactionById(final String id,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePaymentTransactionById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, id);
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
          __preparedStmtOfDeletePaymentTransactionById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PaymentTransactionEntity>> getAllPaymentTransactions() {
    final String _sql = "SELECT * FROM payment_transactions ORDER BY paymentDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payment_transactions"}, new Callable<List<PaymentTransactionEntity>>() {
      @Override
      @NonNull
      public List<PaymentTransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfInvoiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceId");
          final int _cursorIndexOfPaymentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDate");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final List<PaymentTransactionEntity> _result = new ArrayList<PaymentTransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentTransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final String _tmpInvoiceId;
            if (_cursor.isNull(_cursorIndexOfInvoiceId)) {
              _tmpInvoiceId = null;
            } else {
              _tmpInvoiceId = _cursor.getString(_cursorIndexOfInvoiceId);
            }
            final long _tmpPaymentDate;
            _tmpPaymentDate = _cursor.getLong(_cursorIndexOfPaymentDate);
            final double _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
            final String _tmpPaymentMethod;
            _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item = new PaymentTransactionEntity(_tmpId,_tmpRepresentativeId,_tmpInvoiceId,_tmpPaymentDate,_tmpAmountPaid,_tmpPaymentMethod,_tmpNotes,_tmpType);
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
  public Flow<List<PaymentTransactionEntity>> getPaymentTransactionsByRepresentative(
      final String representativeId) {
    final String _sql = "SELECT * FROM payment_transactions WHERE representativeId = ? ORDER BY paymentDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, representativeId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payment_transactions"}, new Callable<List<PaymentTransactionEntity>>() {
      @Override
      @NonNull
      public List<PaymentTransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfInvoiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceId");
          final int _cursorIndexOfPaymentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDate");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final List<PaymentTransactionEntity> _result = new ArrayList<PaymentTransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentTransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final String _tmpInvoiceId;
            if (_cursor.isNull(_cursorIndexOfInvoiceId)) {
              _tmpInvoiceId = null;
            } else {
              _tmpInvoiceId = _cursor.getString(_cursorIndexOfInvoiceId);
            }
            final long _tmpPaymentDate;
            _tmpPaymentDate = _cursor.getLong(_cursorIndexOfPaymentDate);
            final double _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
            final String _tmpPaymentMethod;
            _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item = new PaymentTransactionEntity(_tmpId,_tmpRepresentativeId,_tmpInvoiceId,_tmpPaymentDate,_tmpAmountPaid,_tmpPaymentMethod,_tmpNotes,_tmpType);
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
  public Flow<List<PaymentTransactionEntity>> getPaymentTransactionsByInvoice(
      final String invoiceId) {
    final String _sql = "SELECT * FROM payment_transactions WHERE invoiceId = ? ORDER BY paymentDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, invoiceId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payment_transactions"}, new Callable<List<PaymentTransactionEntity>>() {
      @Override
      @NonNull
      public List<PaymentTransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfInvoiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceId");
          final int _cursorIndexOfPaymentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDate");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final List<PaymentTransactionEntity> _result = new ArrayList<PaymentTransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentTransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final String _tmpInvoiceId;
            if (_cursor.isNull(_cursorIndexOfInvoiceId)) {
              _tmpInvoiceId = null;
            } else {
              _tmpInvoiceId = _cursor.getString(_cursorIndexOfInvoiceId);
            }
            final long _tmpPaymentDate;
            _tmpPaymentDate = _cursor.getLong(_cursorIndexOfPaymentDate);
            final double _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
            final String _tmpPaymentMethod;
            _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item = new PaymentTransactionEntity(_tmpId,_tmpRepresentativeId,_tmpInvoiceId,_tmpPaymentDate,_tmpAmountPaid,_tmpPaymentMethod,_tmpNotes,_tmpType);
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
  public Object getPaymentTransactionById(final String id,
      final Continuation<? super PaymentTransactionEntity> $completion) {
    final String _sql = "SELECT * FROM payment_transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PaymentTransactionEntity>() {
      @Override
      @Nullable
      public PaymentTransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfInvoiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceId");
          final int _cursorIndexOfPaymentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDate");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final PaymentTransactionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final String _tmpInvoiceId;
            if (_cursor.isNull(_cursorIndexOfInvoiceId)) {
              _tmpInvoiceId = null;
            } else {
              _tmpInvoiceId = _cursor.getString(_cursorIndexOfInvoiceId);
            }
            final long _tmpPaymentDate;
            _tmpPaymentDate = _cursor.getLong(_cursorIndexOfPaymentDate);
            final double _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
            final String _tmpPaymentMethod;
            _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _result = new PaymentTransactionEntity(_tmpId,_tmpRepresentativeId,_tmpInvoiceId,_tmpPaymentDate,_tmpAmountPaid,_tmpPaymentMethod,_tmpNotes,_tmpType);
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
  public Flow<List<PaymentTransactionEntity>> getPaymentTransactionsByDateRange(
      final long startDate, final long endDate) {
    final String _sql = "SELECT * FROM payment_transactions WHERE paymentDate BETWEEN ? AND ? ORDER BY paymentDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payment_transactions"}, new Callable<List<PaymentTransactionEntity>>() {
      @Override
      @NonNull
      public List<PaymentTransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfInvoiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceId");
          final int _cursorIndexOfPaymentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDate");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final List<PaymentTransactionEntity> _result = new ArrayList<PaymentTransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentTransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final String _tmpInvoiceId;
            if (_cursor.isNull(_cursorIndexOfInvoiceId)) {
              _tmpInvoiceId = null;
            } else {
              _tmpInvoiceId = _cursor.getString(_cursorIndexOfInvoiceId);
            }
            final long _tmpPaymentDate;
            _tmpPaymentDate = _cursor.getLong(_cursorIndexOfPaymentDate);
            final double _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
            final String _tmpPaymentMethod;
            _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item = new PaymentTransactionEntity(_tmpId,_tmpRepresentativeId,_tmpInvoiceId,_tmpPaymentDate,_tmpAmountPaid,_tmpPaymentMethod,_tmpNotes,_tmpType);
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
  public Flow<List<PaymentTransactionEntity>> getPaymentTransactionsByMethod(
      final String paymentMethod) {
    final String _sql = "SELECT * FROM payment_transactions WHERE paymentMethod = ? ORDER BY paymentDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, paymentMethod);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"payment_transactions"}, new Callable<List<PaymentTransactionEntity>>() {
      @Override
      @NonNull
      public List<PaymentTransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "representativeId");
          final int _cursorIndexOfInvoiceId = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceId");
          final int _cursorIndexOfPaymentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDate");
          final int _cursorIndexOfAmountPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPaid");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final List<PaymentTransactionEntity> _result = new ArrayList<PaymentTransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PaymentTransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRepresentativeId;
            _tmpRepresentativeId = _cursor.getString(_cursorIndexOfRepresentativeId);
            final String _tmpInvoiceId;
            if (_cursor.isNull(_cursorIndexOfInvoiceId)) {
              _tmpInvoiceId = null;
            } else {
              _tmpInvoiceId = _cursor.getString(_cursorIndexOfInvoiceId);
            }
            final long _tmpPaymentDate;
            _tmpPaymentDate = _cursor.getLong(_cursorIndexOfPaymentDate);
            final double _tmpAmountPaid;
            _tmpAmountPaid = _cursor.getDouble(_cursorIndexOfAmountPaid);
            final String _tmpPaymentMethod;
            _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _item = new PaymentTransactionEntity(_tmpId,_tmpRepresentativeId,_tmpInvoiceId,_tmpPaymentDate,_tmpAmountPaid,_tmpPaymentMethod,_tmpNotes,_tmpType);
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
  public Object getTotalPaymentsByRepresentative(final String representativeId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(amountPaid) FROM payment_transactions WHERE representativeId = ?";
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
  public Object getTotalPaymentsForInvoice(final String invoiceId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(amountPaid) FROM payment_transactions WHERE invoiceId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, invoiceId);
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
  public Object getPaymentCountByRepresentative(final String representativeId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM payment_transactions WHERE representativeId = ?";
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

  @Override
  public Object getTotalPaymentsByDateRange(final long startDate, final long endDate,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(amountPaid) FROM payment_transactions WHERE paymentDate BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
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
  public Object getPaymentTransactionCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM payment_transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
