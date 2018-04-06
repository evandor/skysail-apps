package io.skysail.server.app.docker

import org.osgi.framework.Bundle

object DockerProcessDescriptor {

  def apply(b: Bundle): DockerProcessDescriptor = {
    DockerProcessDescriptor(
      b.getBundleId.toString,
      b.getSymbolicName,
      b.getVersion.toString,
      "active",
      0L,
      List(),
      List()
    )
  }
}

case class DockerProcessDescriptor(
                             id: String,
                             symbolicName: String,
                             version: String,
                             state: String,
                             size: Long,
                             registeredServiceIds: List[String],
                             usedServiceIds: List[String]
                           )

case class DockerProcessDescriptorList(bundles: List[DockerProcessDescriptor])