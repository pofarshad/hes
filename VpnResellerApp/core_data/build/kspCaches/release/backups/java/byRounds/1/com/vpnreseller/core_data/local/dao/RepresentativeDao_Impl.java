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
import com.vpnreseller.core_data.local.entity.RepresentativeEntity;
import java.lang.Class;
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
public final class RepresentativeDao_Impl implements RepresentativeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RepresentativeEntity> __insertionAdapterOfRepresentativeEntity;

  private final EntityDeletionOrUpdateAdapter<RepresentativeEntity> __deletionAdapterOfRepresentativeEntity;

  private final EntityDeletionOrUpdateAdapter<RepresentativeEntity> __updateAdapterOfRepresentativeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRepresentativeById;

  public RepresentativeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRepresentativeEntity = new EntityInsertionAdapter<RepresentativeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `representatives` (`id`,`fullName`,`adminUsernameMarzban`,`telegramLink`,`pricePerGbLimited`,`monthlyUnlimitedPrice`,`discountType`,`discountValue`,`parentRepresentativeId`,`defaultSubscriptionType`,`defaultDurationMonths`,`isActive`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RepresentativeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFullName());
        statement.bindString(3, entity.getAdminUsernameMarzban());
        if (entity.getTelegramLink() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTelegramLink());
        }
        statement.bindDouble(5, entity.getPricePerGbLimited());
        statement.bindDouble(6, entity.getMonthlyUnlimitedPrice());
        statement.bindString(7, entity.getDiscountType());
        statement.bindDouble(8, entity.getDiscountValue());
        if (entity.getParentRepresentativeId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getParentRepresentativeId());
        }
        statement.bindString(10, entity.getDefaultSubscriptionType());
        statement.bindLong(11, entity.getDefaultDurationMonths());
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(12, _tmp);
      }
    };
    this.__deletionAdapterOfRepresentativeEntity = new EntityDeletionOrUpdateAdapter<RepresentativeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `representatives` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RepresentativeEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfRepresentativeEntity = new EntityDeletionOrUpdateAdapter<RepresentativeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `representatives` SET `id` = ?,`fullName` = ?,`adminUsernameMarzban` = ?,`telegramLink` = ?,`pricePerGbLimited` = ?,`monthlyUnlimitedPrice` = ?,`discountType` = ?,`discountValue` = ?,`parentRepresentativeId` = ?,`defaultSubscriptionType` = ?,`defaultDurationMonths` = ?,`isActive` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RepresentativeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFullName());
        statement.bindString(3, entity.getAdminUsernameMarzban());
        if (entity.getTelegramLink() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTelegramLink());
        }
        statement.bindDouble(5, entity.getPricePerGbLimited());
        statement.bindDouble(6, entity.getMonthlyUnlimitedPrice());
        statement.bindString(7, entity.getDiscountType());
        statement.bindDouble(8, entity.getDiscountValue());
        if (entity.getParentRepresentativeId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getParentRepresentativeId());
        }
        statement.bindString(10, entity.getDefaultSubscriptionType());
        statement.bindLong(11, entity.getDefaultDurationMonths());
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(12, _tmp);
        statement.bindString(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteRepresentativeById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM representatives WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertRepresentative(final RepresentativeEntity representative,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRepresentativeEntity.insert(representative);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRepresentatives(final List<RepresentativeEntity> representatives,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRepresentativeEntity.insert(representatives);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRepresentative(final RepresentativeEntity representative,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRepresentativeEntity.handle(representative);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRepresentative(final RepresentativeEntity representative,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRepresentativeEntity.handle(representative);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRepresentativeById(final String id,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRepresentativeById.acquire();
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
          __preparedStmtOfDeleteRepresentativeById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RepresentativeEntity>> getAllRepresentatives() {
    final String _sql = "SELECT * FROM representatives ORDER BY fullName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"representatives"}, new Callable<List<RepresentativeEntity>>() {
      @Override
      @NonNull
      public List<RepresentativeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfAdminUsernameMarzban = CursorUtil.getColumnIndexOrThrow(_cursor, "adminUsernameMarzban");
          final int _cursorIndexOfTelegramLink = CursorUtil.getColumnIndexOrThrow(_cursor, "telegramLink");
          final int _cursorIndexOfPricePerGbLimited = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerGbLimited");
          final int _cursorIndexOfMonthlyUnlimitedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyUnlimitedPrice");
          final int _cursorIndexOfDiscountType = CursorUtil.getColumnIndexOrThrow(_cursor, "discountType");
          final int _cursorIndexOfDiscountValue = CursorUtil.getColumnIndexOrThrow(_cursor, "discountValue");
          final int _cursorIndexOfParentRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentRepresentativeId");
          final int _cursorIndexOfDefaultSubscriptionType = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultSubscriptionType");
          final int _cursorIndexOfDefaultDurationMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultDurationMonths");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<RepresentativeEntity> _result = new ArrayList<RepresentativeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RepresentativeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpAdminUsernameMarzban;
            _tmpAdminUsernameMarzban = _cursor.getString(_cursorIndexOfAdminUsernameMarzban);
            final String _tmpTelegramLink;
            if (_cursor.isNull(_cursorIndexOfTelegramLink)) {
              _tmpTelegramLink = null;
            } else {
              _tmpTelegramLink = _cursor.getString(_cursorIndexOfTelegramLink);
            }
            final double _tmpPricePerGbLimited;
            _tmpPricePerGbLimited = _cursor.getDouble(_cursorIndexOfPricePerGbLimited);
            final double _tmpMonthlyUnlimitedPrice;
            _tmpMonthlyUnlimitedPrice = _cursor.getDouble(_cursorIndexOfMonthlyUnlimitedPrice);
            final String _tmpDiscountType;
            _tmpDiscountType = _cursor.getString(_cursorIndexOfDiscountType);
            final double _tmpDiscountValue;
            _tmpDiscountValue = _cursor.getDouble(_cursorIndexOfDiscountValue);
            final String _tmpParentRepresentativeId;
            if (_cursor.isNull(_cursorIndexOfParentRepresentativeId)) {
              _tmpParentRepresentativeId = null;
            } else {
              _tmpParentRepresentativeId = _cursor.getString(_cursorIndexOfParentRepresentativeId);
            }
            final String _tmpDefaultSubscriptionType;
            _tmpDefaultSubscriptionType = _cursor.getString(_cursorIndexOfDefaultSubscriptionType);
            final int _tmpDefaultDurationMonths;
            _tmpDefaultDurationMonths = _cursor.getInt(_cursorIndexOfDefaultDurationMonths);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            _item = new RepresentativeEntity(_tmpId,_tmpFullName,_tmpAdminUsernameMarzban,_tmpTelegramLink,_tmpPricePerGbLimited,_tmpMonthlyUnlimitedPrice,_tmpDiscountType,_tmpDiscountValue,_tmpParentRepresentativeId,_tmpDefaultSubscriptionType,_tmpDefaultDurationMonths,_tmpIsActive);
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
  public Flow<List<RepresentativeEntity>> getActiveRepresentatives() {
    final String _sql = "SELECT * FROM representatives WHERE isActive = 1 ORDER BY fullName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"representatives"}, new Callable<List<RepresentativeEntity>>() {
      @Override
      @NonNull
      public List<RepresentativeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfAdminUsernameMarzban = CursorUtil.getColumnIndexOrThrow(_cursor, "adminUsernameMarzban");
          final int _cursorIndexOfTelegramLink = CursorUtil.getColumnIndexOrThrow(_cursor, "telegramLink");
          final int _cursorIndexOfPricePerGbLimited = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerGbLimited");
          final int _cursorIndexOfMonthlyUnlimitedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyUnlimitedPrice");
          final int _cursorIndexOfDiscountType = CursorUtil.getColumnIndexOrThrow(_cursor, "discountType");
          final int _cursorIndexOfDiscountValue = CursorUtil.getColumnIndexOrThrow(_cursor, "discountValue");
          final int _cursorIndexOfParentRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentRepresentativeId");
          final int _cursorIndexOfDefaultSubscriptionType = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultSubscriptionType");
          final int _cursorIndexOfDefaultDurationMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultDurationMonths");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<RepresentativeEntity> _result = new ArrayList<RepresentativeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RepresentativeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpAdminUsernameMarzban;
            _tmpAdminUsernameMarzban = _cursor.getString(_cursorIndexOfAdminUsernameMarzban);
            final String _tmpTelegramLink;
            if (_cursor.isNull(_cursorIndexOfTelegramLink)) {
              _tmpTelegramLink = null;
            } else {
              _tmpTelegramLink = _cursor.getString(_cursorIndexOfTelegramLink);
            }
            final double _tmpPricePerGbLimited;
            _tmpPricePerGbLimited = _cursor.getDouble(_cursorIndexOfPricePerGbLimited);
            final double _tmpMonthlyUnlimitedPrice;
            _tmpMonthlyUnlimitedPrice = _cursor.getDouble(_cursorIndexOfMonthlyUnlimitedPrice);
            final String _tmpDiscountType;
            _tmpDiscountType = _cursor.getString(_cursorIndexOfDiscountType);
            final double _tmpDiscountValue;
            _tmpDiscountValue = _cursor.getDouble(_cursorIndexOfDiscountValue);
            final String _tmpParentRepresentativeId;
            if (_cursor.isNull(_cursorIndexOfParentRepresentativeId)) {
              _tmpParentRepresentativeId = null;
            } else {
              _tmpParentRepresentativeId = _cursor.getString(_cursorIndexOfParentRepresentativeId);
            }
            final String _tmpDefaultSubscriptionType;
            _tmpDefaultSubscriptionType = _cursor.getString(_cursorIndexOfDefaultSubscriptionType);
            final int _tmpDefaultDurationMonths;
            _tmpDefaultDurationMonths = _cursor.getInt(_cursorIndexOfDefaultDurationMonths);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            _item = new RepresentativeEntity(_tmpId,_tmpFullName,_tmpAdminUsernameMarzban,_tmpTelegramLink,_tmpPricePerGbLimited,_tmpMonthlyUnlimitedPrice,_tmpDiscountType,_tmpDiscountValue,_tmpParentRepresentativeId,_tmpDefaultSubscriptionType,_tmpDefaultDurationMonths,_tmpIsActive);
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
  public Object getRepresentativeById(final String id,
      final Continuation<? super RepresentativeEntity> $completion) {
    final String _sql = "SELECT * FROM representatives WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RepresentativeEntity>() {
      @Override
      @Nullable
      public RepresentativeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfAdminUsernameMarzban = CursorUtil.getColumnIndexOrThrow(_cursor, "adminUsernameMarzban");
          final int _cursorIndexOfTelegramLink = CursorUtil.getColumnIndexOrThrow(_cursor, "telegramLink");
          final int _cursorIndexOfPricePerGbLimited = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerGbLimited");
          final int _cursorIndexOfMonthlyUnlimitedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyUnlimitedPrice");
          final int _cursorIndexOfDiscountType = CursorUtil.getColumnIndexOrThrow(_cursor, "discountType");
          final int _cursorIndexOfDiscountValue = CursorUtil.getColumnIndexOrThrow(_cursor, "discountValue");
          final int _cursorIndexOfParentRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentRepresentativeId");
          final int _cursorIndexOfDefaultSubscriptionType = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultSubscriptionType");
          final int _cursorIndexOfDefaultDurationMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultDurationMonths");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final RepresentativeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpAdminUsernameMarzban;
            _tmpAdminUsernameMarzban = _cursor.getString(_cursorIndexOfAdminUsernameMarzban);
            final String _tmpTelegramLink;
            if (_cursor.isNull(_cursorIndexOfTelegramLink)) {
              _tmpTelegramLink = null;
            } else {
              _tmpTelegramLink = _cursor.getString(_cursorIndexOfTelegramLink);
            }
            final double _tmpPricePerGbLimited;
            _tmpPricePerGbLimited = _cursor.getDouble(_cursorIndexOfPricePerGbLimited);
            final double _tmpMonthlyUnlimitedPrice;
            _tmpMonthlyUnlimitedPrice = _cursor.getDouble(_cursorIndexOfMonthlyUnlimitedPrice);
            final String _tmpDiscountType;
            _tmpDiscountType = _cursor.getString(_cursorIndexOfDiscountType);
            final double _tmpDiscountValue;
            _tmpDiscountValue = _cursor.getDouble(_cursorIndexOfDiscountValue);
            final String _tmpParentRepresentativeId;
            if (_cursor.isNull(_cursorIndexOfParentRepresentativeId)) {
              _tmpParentRepresentativeId = null;
            } else {
              _tmpParentRepresentativeId = _cursor.getString(_cursorIndexOfParentRepresentativeId);
            }
            final String _tmpDefaultSubscriptionType;
            _tmpDefaultSubscriptionType = _cursor.getString(_cursorIndexOfDefaultSubscriptionType);
            final int _tmpDefaultDurationMonths;
            _tmpDefaultDurationMonths = _cursor.getInt(_cursorIndexOfDefaultDurationMonths);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            _result = new RepresentativeEntity(_tmpId,_tmpFullName,_tmpAdminUsernameMarzban,_tmpTelegramLink,_tmpPricePerGbLimited,_tmpMonthlyUnlimitedPrice,_tmpDiscountType,_tmpDiscountValue,_tmpParentRepresentativeId,_tmpDefaultSubscriptionType,_tmpDefaultDurationMonths,_tmpIsActive);
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
  public Flow<List<RepresentativeEntity>> getRepresentativesByParent(final String parentId) {
    final String _sql = "SELECT * FROM representatives WHERE parentRepresentativeId = ? ORDER BY fullName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, parentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"representatives"}, new Callable<List<RepresentativeEntity>>() {
      @Override
      @NonNull
      public List<RepresentativeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfAdminUsernameMarzban = CursorUtil.getColumnIndexOrThrow(_cursor, "adminUsernameMarzban");
          final int _cursorIndexOfTelegramLink = CursorUtil.getColumnIndexOrThrow(_cursor, "telegramLink");
          final int _cursorIndexOfPricePerGbLimited = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerGbLimited");
          final int _cursorIndexOfMonthlyUnlimitedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyUnlimitedPrice");
          final int _cursorIndexOfDiscountType = CursorUtil.getColumnIndexOrThrow(_cursor, "discountType");
          final int _cursorIndexOfDiscountValue = CursorUtil.getColumnIndexOrThrow(_cursor, "discountValue");
          final int _cursorIndexOfParentRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentRepresentativeId");
          final int _cursorIndexOfDefaultSubscriptionType = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultSubscriptionType");
          final int _cursorIndexOfDefaultDurationMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultDurationMonths");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<RepresentativeEntity> _result = new ArrayList<RepresentativeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RepresentativeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpAdminUsernameMarzban;
            _tmpAdminUsernameMarzban = _cursor.getString(_cursorIndexOfAdminUsernameMarzban);
            final String _tmpTelegramLink;
            if (_cursor.isNull(_cursorIndexOfTelegramLink)) {
              _tmpTelegramLink = null;
            } else {
              _tmpTelegramLink = _cursor.getString(_cursorIndexOfTelegramLink);
            }
            final double _tmpPricePerGbLimited;
            _tmpPricePerGbLimited = _cursor.getDouble(_cursorIndexOfPricePerGbLimited);
            final double _tmpMonthlyUnlimitedPrice;
            _tmpMonthlyUnlimitedPrice = _cursor.getDouble(_cursorIndexOfMonthlyUnlimitedPrice);
            final String _tmpDiscountType;
            _tmpDiscountType = _cursor.getString(_cursorIndexOfDiscountType);
            final double _tmpDiscountValue;
            _tmpDiscountValue = _cursor.getDouble(_cursorIndexOfDiscountValue);
            final String _tmpParentRepresentativeId;
            if (_cursor.isNull(_cursorIndexOfParentRepresentativeId)) {
              _tmpParentRepresentativeId = null;
            } else {
              _tmpParentRepresentativeId = _cursor.getString(_cursorIndexOfParentRepresentativeId);
            }
            final String _tmpDefaultSubscriptionType;
            _tmpDefaultSubscriptionType = _cursor.getString(_cursorIndexOfDefaultSubscriptionType);
            final int _tmpDefaultDurationMonths;
            _tmpDefaultDurationMonths = _cursor.getInt(_cursorIndexOfDefaultDurationMonths);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            _item = new RepresentativeEntity(_tmpId,_tmpFullName,_tmpAdminUsernameMarzban,_tmpTelegramLink,_tmpPricePerGbLimited,_tmpMonthlyUnlimitedPrice,_tmpDiscountType,_tmpDiscountValue,_tmpParentRepresentativeId,_tmpDefaultSubscriptionType,_tmpDefaultDurationMonths,_tmpIsActive);
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
  public Flow<List<RepresentativeEntity>> searchRepresentatives(final String query) {
    final String _sql = "SELECT * FROM representatives WHERE fullName LIKE '%' || ? || '%' OR adminUsernameMarzban LIKE '%' || ? || '%' ORDER BY fullName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"representatives"}, new Callable<List<RepresentativeEntity>>() {
      @Override
      @NonNull
      public List<RepresentativeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "fullName");
          final int _cursorIndexOfAdminUsernameMarzban = CursorUtil.getColumnIndexOrThrow(_cursor, "adminUsernameMarzban");
          final int _cursorIndexOfTelegramLink = CursorUtil.getColumnIndexOrThrow(_cursor, "telegramLink");
          final int _cursorIndexOfPricePerGbLimited = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerGbLimited");
          final int _cursorIndexOfMonthlyUnlimitedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyUnlimitedPrice");
          final int _cursorIndexOfDiscountType = CursorUtil.getColumnIndexOrThrow(_cursor, "discountType");
          final int _cursorIndexOfDiscountValue = CursorUtil.getColumnIndexOrThrow(_cursor, "discountValue");
          final int _cursorIndexOfParentRepresentativeId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentRepresentativeId");
          final int _cursorIndexOfDefaultSubscriptionType = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultSubscriptionType");
          final int _cursorIndexOfDefaultDurationMonths = CursorUtil.getColumnIndexOrThrow(_cursor, "defaultDurationMonths");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final List<RepresentativeEntity> _result = new ArrayList<RepresentativeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RepresentativeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            final String _tmpAdminUsernameMarzban;
            _tmpAdminUsernameMarzban = _cursor.getString(_cursorIndexOfAdminUsernameMarzban);
            final String _tmpTelegramLink;
            if (_cursor.isNull(_cursorIndexOfTelegramLink)) {
              _tmpTelegramLink = null;
            } else {
              _tmpTelegramLink = _cursor.getString(_cursorIndexOfTelegramLink);
            }
            final double _tmpPricePerGbLimited;
            _tmpPricePerGbLimited = _cursor.getDouble(_cursorIndexOfPricePerGbLimited);
            final double _tmpMonthlyUnlimitedPrice;
            _tmpMonthlyUnlimitedPrice = _cursor.getDouble(_cursorIndexOfMonthlyUnlimitedPrice);
            final String _tmpDiscountType;
            _tmpDiscountType = _cursor.getString(_cursorIndexOfDiscountType);
            final double _tmpDiscountValue;
            _tmpDiscountValue = _cursor.getDouble(_cursorIndexOfDiscountValue);
            final String _tmpParentRepresentativeId;
            if (_cursor.isNull(_cursorIndexOfParentRepresentativeId)) {
              _tmpParentRepresentativeId = null;
            } else {
              _tmpParentRepresentativeId = _cursor.getString(_cursorIndexOfParentRepresentativeId);
            }
            final String _tmpDefaultSubscriptionType;
            _tmpDefaultSubscriptionType = _cursor.getString(_cursorIndexOfDefaultSubscriptionType);
            final int _tmpDefaultDurationMonths;
            _tmpDefaultDurationMonths = _cursor.getInt(_cursorIndexOfDefaultDurationMonths);
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            _item = new RepresentativeEntity(_tmpId,_tmpFullName,_tmpAdminUsernameMarzban,_tmpTelegramLink,_tmpPricePerGbLimited,_tmpMonthlyUnlimitedPrice,_tmpDiscountType,_tmpDiscountValue,_tmpParentRepresentativeId,_tmpDefaultSubscriptionType,_tmpDefaultDurationMonths,_tmpIsActive);
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
  public Object getRepresentativeCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM representatives";
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

  @Override
  public Object getActiveRepresentativeCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM representatives WHERE isActive = 1";
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
