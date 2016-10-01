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
    public class tblReservationsController : ApiController
    {
        private dbPerishableEntities db = new dbPerishableEntities();

        // GET: api/tblReservations
        public IQueryable<tblReservation> GettblReservations()
        {
            return db.tblReservations;
        }

        // GET: api/tblReservations/5
        [ResponseType(typeof(tblReservation))]
        public IHttpActionResult GettblReservation(int id)
        {
            tblReservation tblReservation = db.tblReservations.Find(id);
            if (tblReservation == null)
            {
                return NotFound();
            }

            return Ok(tblReservation);
        }

        // PUT: api/tblReservations/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PuttblReservation(int id, tblReservation tblReservation)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != tblReservation.Id)
            {
                return BadRequest();
            }

            db.Entry(tblReservation).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!tblReservationExists(id))
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

        // POST: api/tblReservations
        [ResponseType(typeof(tblReservation))]
        public IHttpActionResult PosttblReservation(tblReservation tblReservation)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.tblReservations.Add(tblReservation);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = tblReservation.Id }, tblReservation);
        }

        // DELETE: api/tblReservations/5
        [ResponseType(typeof(tblReservation))]
        public IHttpActionResult DeletetblReservation(int id)
        {
            tblReservation tblReservation = db.tblReservations.Find(id);
            if (tblReservation == null)
            {
                return NotFound();
            }

            db.tblReservations.Remove(tblReservation);
            db.SaveChanges();

            return Ok(tblReservation);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool tblReservationExists(int id)
        {
            return db.tblReservations.Count(e => e.Id == id) > 0;
        }
    }
}