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
    public class tblCharitiesController : ApiController
    {
        private dbPerishableEntities db = new dbPerishableEntities();

        // GET: api/tblCharities
        public IQueryable<tblCharity> GettblCharities()
        {
            return db.tblCharities;
        }

        // GET: api/tblCharities/5
        [ResponseType(typeof(tblCharity))]
        public IHttpActionResult GettblCharity(int id)
        {
            tblCharity tblCharity = db.tblCharities.Find(id);
            if (tblCharity == null)
            {
                return NotFound();
            }

            return Ok(tblCharity);
        }

        // PUT: api/tblCharities/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PuttblCharity(int id, tblCharity tblCharity)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tblCharity.Id)
            {
                return BadRequest();
            }

            db.Entry(tblCharity).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!tblCharityExists(id))
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

        // POST: api/tblCharities
        [ResponseType(typeof(tblCharity))]
        public IHttpActionResult PosttblCharity(tblCharity tblCharity)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.tblCharities.Add(tblCharity);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = tblCharity.Id }, tblCharity);
        }

        // DELETE: api/tblCharities/5
        [ResponseType(typeof(tblCharity))]
        public IHttpActionResult DeletetblCharity(int id)
        {
            tblCharity tblCharity = db.tblCharities.Find(id);
            if (tblCharity == null)
            {
                return NotFound();
            }

            db.tblCharities.Remove(tblCharity);
            db.SaveChanges();

            return Ok(tblCharity);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool tblCharityExists(int id)
        {
            return db.tblCharities.Count(e => e.Id == id) > 0;
        }
    }
}