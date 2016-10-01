namespace PerishableApp.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("tblPlace")]
    public partial class tblPlace
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public tblPlace()
        {
            tblCharities = new HashSet<tblCharity>();
            tblCompanies = new HashSet<tblCompany>();
        }

        public int Id { get; set; }

        [Required]
        [StringLength(75)]
        public string Name { get; set; }

        [StringLength(10)]
        public string Phone { get; set; }

        [StringLength(75)]
        public string Email { get; set; }

        [StringLength(100)]
        public string Address { get; set; }

        [StringLength(75)]
        public string City { get; set; }

        [StringLength(2)]
        public string Province { get; set; }

        [StringLength(75)]
        public string Country { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<tblCharity> tblCharities { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<tblCompany> tblCompanies { get; set; }
    }
}
