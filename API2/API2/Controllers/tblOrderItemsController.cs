using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using API2.Models;

namespace API2.Controllers
{
    public class tblOrderItemsController : ApiController
    {
        private dbPerishableEntities db = new dbPerishableEntities();

        // GET: api/tblOrderItems
        public IQueryable<tblOrderItem> GettblOrderItems()
        {
            return db.tblOrderItems;
        }

        // GET: api/tblOrderItems/5
        [ResponseType(typeof(tblOrderItem))]
        public IHttpActionResult GettblOrderItem(int id)
        {
            tblOrderItem tblOrderItem = db.tblOrderItems.Find(id);
            if (tblOrderItem == null)
            {
                return NotFound();
            }

            return Ok(tblOrderItem);
        }

        // PUT: api/tblOrderItems/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PuttblOrderItem(int id, tblOrderItem tblOrderItem)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tblOrderItem.Id)
            {
                return BadRequest();
            }

            db.Entry(tblOrderItem).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!tblOrderItemExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/tblOrderItems
        [ResponseType(typeof(tblOrderItem))]
        public IHttpActionResult PosttblOrderItem(tblOrderItem tblOrderItem)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.tblOrderItems.Add(tblOrderItem);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = tblOrderItem.Id }, tblOrderItem);
        }

        // DELETE: api/tblOrderItems/5
        [ResponseType(typeof(tblOrderItem))]
        public IHttpActionResult DeletetblOrderItem(int id)
        {
            tblOrderItem tblOrderItem = db.tblOrderItems.Find(id);
            if (tblOrderItem == null)
            {
                return NotFound();
            }

            db.tblOrderItems.Remove(tblOrderItem);
            db.SaveChanges();

            return Ok(tblOrderItem);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool tblOrderItemExists(int id)
        {
            return db.tblOrderItems.Count(e => e.Id == id) > 0;
        }
    }
}