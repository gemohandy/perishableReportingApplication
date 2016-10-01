namespace PerishableApp.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Perishableapp : DbContext
    {
        public Perishableapp()
            : base("name=Perishableappcodefirst")
        {
        }

        public virtual DbSet<tblCharity> tblCharities { get; set; }
        public virtual DbSet<tblCompany> tblCompanies { get; set; }
        public virtual DbSet<tblOrder> tblOrders { get; set; }
        public virtual DbSet<tblOrderItem> tblOrderItems { get; set; }
        public virtual DbSet<tblPlace> tblPlaces { get; set; }
        public virtual DbSet<tblReservation> tblReservations { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<tblCharity>()
                .HasMany(e => e.tblReservations)
                .WithRequired(e => e.tblCharity)
                .HasForeignKey(e => e.fk_CharityID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<tblCompany>()
                .HasMany(e => e.tblOrders)
                .WithRequired(e => e.tblCompany)
                .HasForeignKey(e => e.fk_CompanyID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<tblOrder>()
                .HasMany(e => e.tblOrderItems)
                .WithRequired(e => e.tblOrder)
                .HasForeignKey(e => e.fk_OrderID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<tblOrder>()
                .HasMany(e => e.tblReservations)
                .WithRequired(e => e.tblOrder)
                .HasForeignKey(e => e.fk_OrderID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<tblOrderItem>()
                .Property(e => e.Name)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .Property(e => e.Name)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .Property(e => e.Phone)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .Property(e => e.Address)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .Property(e => e.City)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .Property(e => e.Province)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .Property(e => e.Country)
                .IsUnicode(false);

            modelBuilder.Entity<tblPlace>()
                .HasMany(e => e.tblCharities)
                .WithRequired(e => e.tblPlace)
                .HasForeignKey(e => e.fk_PlaceID)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<tblPlace>()
                .HasMany(e => e.tblCompanies)
                .WithRequired(e => e.tblPlace)
                .HasForeignKey(e => e.fk_PlaceID)
                .WillCascadeOnDelete(false);
        }
    }
}
