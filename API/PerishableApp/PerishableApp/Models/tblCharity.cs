namespace PerishableApp.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("tblCharity")]
    public partial class tblCharity
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public tblCharity()
        {
            tblReservations = new HashSet<tblReservation>();
        }

        public int Id { get; set; }

        public int fk_PlaceID { get; set; }

        public virtual tblPlace tblPlace { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<tblReservation> tblReservations { get; set; }
    }
}
