namespace PerishableApp.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("tblOrderItem")]
    public partial class tblOrderItem
    {
        public int Id { get; set; }

        [Required]
        [StringLength(150)]
        public string Name { get; set; }

        public int? Quantity { get; set; }

        public int fk_OrderID { get; set; }

        public virtual tblOrder tblOrder { get; set; }
    }
}
