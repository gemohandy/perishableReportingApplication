namespace PerishableApp.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Perishableapp2 : DbContext
    {
        public Perishableapp2()
            : base("name=Perishableapp2")
        {
        }

        public virtual DbSet<tblLogin> tblLogins { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<tblLogin>()
                .Property(e => e.Username)
                .IsUnicode(false);

            modelBuilder.Entity<tblLogin>()
                .Property(e => e.Password)
                .IsUnicode(false);

            modelBuilder.Entity<tblLogin>()
                .Property(e => e.PasswordHash)
                .IsUnicode(false);

            modelBuilder.Entity<tblLogin>()
                .Property(e => e.Name)
                .IsUnicode(false);

            modelBuilder.Entity<tblLogin>()
                .Property(e => e.Phone)
                .IsUnicode(false);

            modelBuilder.Entity<tblLogin>()
                .Property(e => e.Email)
                .IsUnicode(false);
        }
    }
}
