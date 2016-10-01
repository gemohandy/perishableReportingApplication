namespace PerishableApp.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("tblReservation")]
    public partial class tblReservation
    {
        public int Id { get; set; }

        public DateTime DateTime { get; set; }

        public bool isActive { get; set; }

        public int fk_OrderID { get; set; }

        public int fk_CharityID { get; set; }

        public virtual tblCharity tblCharity { get; set; }

        public virtual tblOrder tblOrder { get; set; }
    }
}
