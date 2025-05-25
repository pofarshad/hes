package com.vpnreseller.core_data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.vpnreseller.core_data.local.dao.InvoiceDao;
import com.vpnreseller.core_data.local.dao.InvoiceDao_Impl;
import com.vpnreseller.core_data.local.dao.PaymentTransactionDao;
import com.vpnreseller.core_data.local.dao.PaymentTransactionDao_Impl;
import com.vpnreseller.core_data.local.dao.RepresentativeDao;
import com.vpnreseller.core_data.local.dao.RepresentativeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VpnResellerDatabase_Impl extends VpnResellerDatabase {
  private volatile RepresentativeDao _representativeDao;

  private volatile InvoiceDao _invoiceDao;

  private volatile PaymentTransactionDao _paymentTransactionDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `representatives` (`id` TEXT NOT NULL, `fullName` TEXT NOT NULL, `adminUsernameMarzban` TEXT NOT NULL, `telegramLink` TEXT, `pricePerGbLimited` REAL NOT NULL, `monthlyUnlimitedPrice` REAL NOT NULL, `discountType` TEXT NOT NULL, `discountValue` REAL NOT NULL, `parentRepresentativeId` TEXT, `defaultSubscriptionType` TEXT NOT NULL, `defaultDurationMonths` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `invoice_headers` (`id` TEXT NOT NULL, `representativeId` TEXT NOT NULL, `generationDate` INTEGER NOT NULL, `totalAmount` REAL NOT NULL, `status` TEXT NOT NULL, `isSent` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `invoice_line_items` (`id` TEXT NOT NULL, `invoiceHeaderId` TEXT NOT NULL, `description` TEXT NOT NULL, `quantity` INTEGER NOT NULL, `unitPrice` REAL NOT NULL, `totalPrice` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `payment_transactions` (`id` TEXT NOT NULL, `representativeId` TEXT NOT NULL, `invoiceId` TEXT, `paymentDate` INTEGER NOT NULL, `amountPaid` REAL NOT NULL, `paymentMethod` TEXT NOT NULL, `notes` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '200563d4be990fe87d767307b5a60500')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `representatives`");
        db.execSQL("DROP TABLE IF EXISTS `invoice_headers`");
        db.execSQL("DROP TABLE IF EXISTS `invoice_line_items`");
        db.execSQL("DROP TABLE IF EXISTS `payment_transactions`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRepresentatives = new HashMap<String, TableInfo.Column>(12);
        _columnsRepresentatives.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("fullName", new TableInfo.Column("fullName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("adminUsernameMarzban", new TableInfo.Column("adminUsernameMarzban", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("telegramLink", new TableInfo.Column("telegramLink", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("pricePerGbLimited", new TableInfo.Column("pricePerGbLimited", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("monthlyUnlimitedPrice", new TableInfo.Column("monthlyUnlimitedPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("discountType", new TableInfo.Column("discountType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("discountValue", new TableInfo.Column("discountValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("parentRepresentativeId", new TableInfo.Column("parentRepresentativeId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("defaultSubscriptionType", new TableInfo.Column("defaultSubscriptionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("defaultDurationMonths", new TableInfo.Column("defaultDurationMonths", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRepresentatives.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRepresentatives = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRepresentatives = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRepresentatives = new TableInfo("representatives", _columnsRepresentatives, _foreignKeysRepresentatives, _indicesRepresentatives);
        final TableInfo _existingRepresentatives = TableInfo.read(db, "representatives");
        if (!_infoRepresentatives.equals(_existingRepresentatives)) {
          return new RoomOpenHelper.ValidationResult(false, "representatives(com.vpnreseller.core_data.local.entity.RepresentativeEntity).\n"
                  + " Expected:\n" + _infoRepresentatives + "\n"
                  + " Found:\n" + _existingRepresentatives);
        }
        final HashMap<String, TableInfo.Column> _columnsInvoiceHeaders = new HashMap<String, TableInfo.Column>(6);
        _columnsInvoiceHeaders.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceHeaders.put("representativeId", new TableInfo.Column("representativeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceHeaders.put("generationDate", new TableInfo.Column("generationDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceHeaders.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceHeaders.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceHeaders.put("isSent", new TableInfo.Column("isSent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvoiceHeaders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvoiceHeaders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvoiceHeaders = new TableInfo("invoice_headers", _columnsInvoiceHeaders, _foreignKeysInvoiceHeaders, _indicesInvoiceHeaders);
        final TableInfo _existingInvoiceHeaders = TableInfo.read(db, "invoice_headers");
        if (!_infoInvoiceHeaders.equals(_existingInvoiceHeaders)) {
          return new RoomOpenHelper.ValidationResult(false, "invoice_headers(com.vpnreseller.core_data.local.entity.InvoiceHeaderEntity).\n"
                  + " Expected:\n" + _infoInvoiceHeaders + "\n"
                  + " Found:\n" + _existingInvoiceHeaders);
        }
        final HashMap<String, TableInfo.Column> _columnsInvoiceLineItems = new HashMap<String, TableInfo.Column>(6);
        _columnsInvoiceLineItems.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceLineItems.put("invoiceHeaderId", new TableInfo.Column("invoiceHeaderId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceLineItems.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceLineItems.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceLineItems.put("unitPrice", new TableInfo.Column("unitPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoiceLineItems.put("totalPrice", new TableInfo.Column("totalPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvoiceLineItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvoiceLineItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvoiceLineItems = new TableInfo("invoice_line_items", _columnsInvoiceLineItems, _foreignKeysInvoiceLineItems, _indicesInvoiceLineItems);
        final TableInfo _existingInvoiceLineItems = TableInfo.read(db, "invoice_line_items");
        if (!_infoInvoiceLineItems.equals(_existingInvoiceLineItems)) {
          return new RoomOpenHelper.ValidationResult(false, "invoice_line_items(com.vpnreseller.core_data.local.entity.InvoiceLineItemEntity).\n"
                  + " Expected:\n" + _infoInvoiceLineItems + "\n"
                  + " Found:\n" + _existingInvoiceLineItems);
        }
        final HashMap<String, TableInfo.Column> _columnsPaymentTransactions = new HashMap<String, TableInfo.Column>(7);
        _columnsPaymentTransactions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentTransactions.put("representativeId", new TableInfo.Column("representativeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentTransactions.put("invoiceId", new TableInfo.Column("invoiceId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentTransactions.put("paymentDate", new TableInfo.Column("paymentDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentTransactions.put("amountPaid", new TableInfo.Column("amountPaid", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentTransactions.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPaymentTransactions.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPaymentTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPaymentTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPaymentTransactions = new TableInfo("payment_transactions", _columnsPaymentTransactions, _foreignKeysPaymentTransactions, _indicesPaymentTransactions);
        final TableInfo _existingPaymentTransactions = TableInfo.read(db, "payment_transactions");
        if (!_infoPaymentTransactions.equals(_existingPaymentTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "payment_transactions(com.vpnreseller.core_data.local.entity.PaymentTransactionEntity).\n"
                  + " Expected:\n" + _infoPaymentTransactions + "\n"
                  + " Found:\n" + _existingPaymentTransactions);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "200563d4be990fe87d767307b5a60500", "289a85f9a0c1b2470b773b131062e5fb");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "representatives","invoice_headers","invoice_line_items","payment_transactions");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `representatives`");
      _db.execSQL("DELETE FROM `invoice_headers`");
      _db.execSQL("DELETE FROM `invoice_line_items`");
      _db.execSQL("DELETE FROM `payment_transactions`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RepresentativeDao.class, RepresentativeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InvoiceDao.class, InvoiceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PaymentTransactionDao.class, PaymentTransactionDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RepresentativeDao representativeDao() {
    if (_representativeDao != null) {
      return _representativeDao;
    } else {
      synchronized(this) {
        if(_representativeDao == null) {
          _representativeDao = new RepresentativeDao_Impl(this);
        }
        return _representativeDao;
      }
    }
  }

  @Override
  public InvoiceDao invoiceDao() {
    if (_invoiceDao != null) {
      return _invoiceDao;
    } else {
      synchronized(this) {
        if(_invoiceDao == null) {
          _invoiceDao = new InvoiceDao_Impl(this);
        }
        return _invoiceDao;
      }
    }
  }

  @Override
  public PaymentTransactionDao paymentTransactionDao() {
    if (_paymentTransactionDao != null) {
      return _paymentTransactionDao;
    } else {
      synchronized(this) {
        if(_paymentTransactionDao == null) {
          _paymentTransactionDao = new PaymentTransactionDao_Impl(this);
        }
        return _paymentTransactionDao;
      }
    }
  }
}
